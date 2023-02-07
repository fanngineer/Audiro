package com.a402.audirochat.controller;

import com.a402.audirochat.dto.ChannelThumbnailDTO;
import com.a402.audirochat.dto.MessageDTO;
import com.a402.audirochat.service.ChatService;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController()
public class ChannelController {

    private final ChatService chatService;

    @GetMapping("/channel/list")
    public ResponseEntity<?> getChanneThumbnailList(@RequestParam String userId){
        try{
            List<ChannelThumbnailDTO> channelThumbnailList = chatService.getChannelThumbnail(userId);
            return ResponseEntity.ok().body(channelThumbnailList);
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/message")
    public ResponseEntity<?> getAllMessages(@RequestParam String channelId){
        try{
            List<MessageDTO> messageDTOList = chatService.getChannelMessages(channelId);
            return ResponseEntity.ok().body(messageDTOList);
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.ok().body(e.getMessage());
        }
    }

    @PostMapping("channel")
    @Transactional
    public ResponseEntity<?> createNewChannel(@RequestBody(required = true) MessageDTO messageDTO){
        try{
            messageDTO.isReceiverValid();
            String channelId = chatService.createChannel(messageDTO);
            chatService.saveMessage(channelId, messageDTO);
            return ResponseEntity.ok().body("success");
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @MessageMapping("/channel/{channel}")
    @SendTo("/sub/{channel}")
    public MessageDTO sendMessage(@DestinationVariable("channel") String channelId, MessageDTO messageDTO){
        chatService.saveMessage(channelId, messageDTO);
        return messageDTO;
    }
}