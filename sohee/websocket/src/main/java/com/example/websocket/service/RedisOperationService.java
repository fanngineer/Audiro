package com.example.websocket.service;

import com.example.websocket.dto.Channel;
import com.example.websocket.dto.ChannelList;
import com.example.websocket.dto.SendMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisOperationService {

    private final RedisTemplate<String, Object> redisTemplate;
    private SetOperations<String, Object> setOperations;
    private ListOperations<String, Object> listOperations;
    private HashOperations<String, Object, Object> hashOperations;

    @PostConstruct
    private void init(){
        setOperations = redisTemplate.opsForSet();
        listOperations = redisTemplate.opsForList();
    }

    // 메세지 저장 (Sorted Map) -> 삭제 기간 설정?
    public void saveMessage(String cid, String sender, String content){
        SendMessage msg = new SendMessage(sender, content);
        hashOperations.put(cid, msg.getMid(), msg);
        listOperations.rightPush(cid, msg.getMid());
    }

    @Transactional
    // 채널 생성 + 유저 저장 (Set)
    public String createChannel(String uid1, String uid2){
        Channel channel = Channel.createChannel();
        log.warn("channel id: "+channel.getChannelId());
        // 채널에 유저 저장
        setOperations.add(channel.getChannelId(), uid1);
        setOperations.add(channel.getChannelId(), uid2);

        // 유저에 채널 저장
        setOperations.add(uid1, channel.getChannelId());
        setOperations.add(uid2, channel.getChannelId());

        return channel.getChannelId();
    }

    // 유저의 채널 리스트 + 마지막 메세지 반환
    public List<ChannelList> getChannelList(String uid){
        List<Channel> channels = setOperations.members(uid).stream()
                .map(c -> (Channel)c).collect(Collectors.toList());

        List<ChannelList> list = setOperations.members(uid).stream()
                .map(c -> ChannelList.builder()
                        .channelId(((Channel)c).getChannelId())
                        .content(((SendMessage)listOperations.index(((Channel)c).getChannelId(), -1)).getContent())
                        .build())
                .collect(Collectors.toList());

        return list;
    }

    // 특정 채널 내 메세지 반환
    public List<SendMessage> getAllMessages(String cid){
        return null;
    }

}
