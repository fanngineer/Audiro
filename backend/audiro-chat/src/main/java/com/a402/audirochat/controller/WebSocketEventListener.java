package com.a402.audirochat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Slf4j
@Component
public class WebSocketEventListener {

    @EventListener
    public void webSocketConnectionHandler(SessionConnectedEvent event){
        log.info("소켓이 연결되었습니다.");
        log.info(event.toString());
    }

    @EventListener
    public void webSocketDisconnectionHandelr(SessionDisconnectEvent event){
        log.info("소켓 연결이 해제되었습니다.");
        log.info(event.toString());
    }
}
