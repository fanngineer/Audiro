package com.example.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class StompController {

    @Autowired
    private SimpMessagingTemplate template;

    @GetMapping("/hi")
    @MessageMapping("/25")
    public void sendMessage(String message){
        log.info("메세지를 보냅니다.");
        template.convertAndSend("/sub/25", message);
        log.info("메세지 전송이 완료되었습니다. : " + message);
    }
}
