package com.a402.audirochat.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ChannelMessage implements Serializable {

    private long userId;
//    private long receiverId;
    private String userNickname;
    private ContentType contentType;
    private String content;
    LocalDateTime sendTime;

    public ChannelMessage(long userId, String userNickname, ContentType type, String content){
        this.userId = userId;
//        this.receiverId = receiverId;
        this.userNickname = userNickname;
        this.contentType = type;
        this.content = content;
        this.sendTime = LocalDateTime.now();
    }
}