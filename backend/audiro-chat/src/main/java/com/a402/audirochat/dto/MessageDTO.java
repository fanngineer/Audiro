package com.a402.audirochat.dto;

import com.a402.audirochat.entity.ContentType;
import com.a402.audirochat.exception.IdNullException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class MessageDTO {

    @NotNull
    private long userId;

    @NotNull
    private long receiverId;

    @NotNull
    private String userNickname;

    @NotNull
    private ContentType contentType;

    private String content;

    @JsonFormat(pattern="yyyy-MM-dd a hh:mm")
    LocalDateTime sendTime;

//    public void isReceiverValid(){
//        if(receiverId == null) throw new IdNullException();
//    }
    public void setSendTime(){
        this.sendTime = LocalDateTime.now();
    }

    public void setContent(String content) {
        this.content = content;
    }
}
