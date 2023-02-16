package com.a402.audiro.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class SpotLoginController {

    private final SimpMessagingTemplate template;

    public void sendMessageToSpot(String spotId, String token){
        log.info("부스로 토큰 보냅니다 : {}", spotId);
        template.convertAndSend("/sub/spot/"+spotId, token);
        log.info("토큰이 부스로 전달되었습니다. : {}", token);
    }

}
