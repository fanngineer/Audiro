package com.example.websocket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Slf4j
public class SomeController {

    @Autowired
    private SimpMessagingTemplate template;

    @Scheduled(fixedDelayString = "1000")
    public void blastToClientsHostReport(){
        log.info("왜 안보내질까 으하ㅏ하");
        template.convertAndSend("/something-to-subscribe-to", "hello world");
    }
}
