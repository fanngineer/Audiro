package com.example.websocket.repository;

import com.example.websocket.dto.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ChannelRepository {

    public List<Channel> findAllChannels(){
        return null;
    }

    public Channel findById(String id){
        return null;
    }

    public Channel createChannel(String name){
        //Channel channel = Channel.create();
        // Redis에 저장하는 코드 필요
        return null;
    }
}
