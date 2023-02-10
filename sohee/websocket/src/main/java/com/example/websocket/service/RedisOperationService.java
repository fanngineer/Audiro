package com.example.websocket.service;

import com.example.websocket.dto.Channel;
import com.example.websocket.dto.ChannelList;
import com.example.websocket.dto.SendMessage;
import com.example.websocket.repository.SendMessageRepository;
import java.util.Set;
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
    private final SendMessageRepository sendMessageRepository;

    @PostConstruct
    private void init(){
        setOperations = redisTemplate.opsForSet();
        listOperations = redisTemplate.opsForList();
    }

    // 메세지 저장 (Sorted Map) -> 삭제 기간 설정?
    public void saveMessage(String cid, String sender, String content){
        SendMessage msg = new SendMessage(sender, content);
        listOperations.rightPush(cid+"message", msg.getMid());
        log.warn(msg.toString());
        sendMessageRepository.save(msg);
        log.warn(msg.toString());
        log.warn("service");
    }

    @Transactional
    // 채널 생성 + 유저 저장 (Set)
    public String createChannel(String uid1, String uid2){
        Channel channel = Channel.createChannel();
        // 채널에 유저 저장
        setOperations.add(channel.getChannelId() + "user", uid1);
        setOperations.add(channel.getChannelId()+"user", uid2);

        // 유저에 채널 저장
        setOperations.add(uid1+"channel", channel.getChannelId());
        setOperations.add(uid2+"channel", channel.getChannelId());

        return channel.getChannelId();
    }

    public List<SendMessage> getAllMessages(String cid, String uid){
        return null;
    }

    // 유저의 채널 리스트 + 마지막 메세지 반환
    public Set<ChannelList> getChannelList(String uid){
//        List<ChannelList> list = setOperations.members(uid+"channel").stream()
//                .map(c -> ChannelList.builder()
//                        .channelId(((String)c))
//                        .content(((SendMessage)listOperations.index((String)c, -1)).getContent())
//                        .build())
//                .collect(Collectors.toList());
        Set<ChannelList> list = setOperations.members(uid+"channel")
                .stream()
                .map(c -> ChannelList.builder().channelId((String)c)
                        .build())
                .collect(Collectors.toSet());
        return list;
    }

    // 특정 채널 내 메세지 반환
    public List<SendMessage> getAllMessages(String cid){
        return null;
    }

}
