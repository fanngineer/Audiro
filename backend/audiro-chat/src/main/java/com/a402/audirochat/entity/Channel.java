package com.a402.audirochat.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Slf4j
@RedisHash("channel")
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Channel implements Serializable {
    @Id
    private String Id;
    private List<ChannelMessage> messages = new ArrayList<>();

    public Channel(){
        this.Id = UUID.randomUUID().toString();
    }

    public void addChannelMessage(ChannelMessage message){
        this.messages.add(message);
    }

    public String getLastMessage(){
        log.info("메세지 사이즈 : " + this.messages.size());
        if(this.messages.size() == 0) return null;
        if(this.messages.get(this.messages.size() - 1).getContentType() == ContentType.IMAGE)
            return "이미지";
        return this.messages.get(this.messages.size() - 1).getContent();
    }

    public LocalDateTime getLastMessageTime(){
        if(this.messages.size() == 0) return null;
        return this.messages.get(this.messages.size() - 1).getSendTime();
    }
}
