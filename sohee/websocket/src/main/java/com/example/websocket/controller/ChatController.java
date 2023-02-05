package com.example.websocket.controller;

import com.example.websocket.dto.ResponseMessage;
import com.example.websocket.dto.SendMessage;
import com.example.websocket.service.RedisOperationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {

    private final RedisOperationService redisOperationService;

    @GetMapping("/chat")
    public ResponseEntity<?> getChannel(@RequestParam("user1") String m1, @RequestParam("user2") String m2){
        log.warn("user1: "+m1);
        log.warn("user1: "+m2);
        try{
            String str = redisOperationService.createChannel(m1, m2);
            return ResponseEntity.ok().body(str);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/msg")
    public ResponseEntity<?> setMessage(@RequestParam("cid") String cid, @RequestParam("sender")String sender, @RequestParam("content") String content){
        try{
            redisOperationService.saveMessage(cid, sender, content);
            return ResponseEntity.ok().body("success");
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @MessageMapping("/{channel}")
    @SendTo("/sub/{channel}")
    public ResponseMessage sendMessage(String userId, @DestinationVariable("channel") String channelId, SendMessage msg){
        // Redis에 저장하는 코드 필요
        log.warn(msg.getContent());
        return new ResponseMessage(userId, msg.getContent(), msg.getSendTime());
    }
}
