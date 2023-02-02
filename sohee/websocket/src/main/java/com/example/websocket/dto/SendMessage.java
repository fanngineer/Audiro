package com.example.websocket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;
import java.util.UUID;

@RedisHash("message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SendMessage {

    @Id
    private String mid;
    private String sender;
    private String senderNickname;
    private String content;
    private LocalDateTime sendTime;

    public SendMessage(String sender, String content){
        this.mid = UUID.randomUUID().toString();
        this.sender = sender;
        this.content = content;
        this.sendTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "SendMessage{" +
                "sender='" + sender + '\'' +
                ", content='" + content + '\'' +
                ", sendTime=" + sendTime +
                '}';
    }
}
