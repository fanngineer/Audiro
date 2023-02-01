package com.example.websocket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ResponseMessage {

    String sender;
    String content;
    LocalDateTime sendTime;
}
