package com.example.websocket.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("message")
@Data
@Builder
public class Message {

    @Id
    private String id;
    private String cid;
    private String uid;
    private String content;
}
