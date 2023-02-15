package com.a402.audirochat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StompHandler implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel){
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        String sessionId = (String)message.getHeaders().get("simpSessionId");

        if(StompCommand.CONNECT == accessor.getCommand()){
            log.info("stomp 연결 완료!");
        }else if(StompCommand.SUBSCRIBE == accessor.getCommand()) {
            log.info("stomp subscribe!");
        }else if (StompCommand.DISCONNECT == accessor.getCommand()){
            log.info("stomp 연결 종료!");
        }
        return message;
    }
}
