package com.a402.audirochat.controller;

import com.a402.audirochat.dto.ChannelThumbnailDTO;
import com.a402.audirochat.dto.MessageDTO;
import com.a402.audirochat.service.ChatService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@RestController()
@CrossOrigin("*")
public class ChannelController {

    private final ChatService chatService;

    @GetMapping("/chat/channel/list")
    public ResponseEntity<?> getChanneThumbnailList(@RequestParam long userId){
        try{
            List<ChannelThumbnailDTO> channelThumbnailList = chatService.getChannelThumbnail(userId);
            log.info("채팅 컨트롤러 - 프로필 이미지 : " + channelThumbnailList.get(0).getProfileImg());
            return ResponseEntity.ok().body(channelThumbnailList);
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/chat/message")
    public ResponseEntity<?> getAllMessages(@RequestParam("channelId") String channelId){
        try{
            log.info("컨트롤러 - 채널 ID : {}", channelId);
            List<MessageDTO> messageDTOList = chatService.getChannelMessages(channelId);
            log.info("메세지 내용 : " + messageDTOList.get(0).toString());
            return ResponseEntity.ok().body(messageDTOList);
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.ok().body(e.getMessage());
        }
    }

    @PostMapping("/chat/channel")
    @Transactional
    public ResponseEntity<?> createNewChannel(@RequestParam("postcardImg") MultipartFile postcardImg, @RequestParam("postcard") String message){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            MessageDTO messageDTO = objectMapper.readValue(message, MessageDTO.class);
            log.info("메세지를 받았습니다. " + messageDTO.toString());
            messageDTO.setSendTime();
            log.info("채널 생성을 시작합니다.");
            String channelId = chatService.createChannel(messageDTO);
//            log.info("채널 생성 : " + channelId);
//            chatService.saveMessage(channelId, messageDTO);
//            log.info("메세지 저장");
            chatService.savePostcard(postcardImg, channelId, messageDTO);
            return ResponseEntity.ok().body("success");

        }catch(IOException e) {
            log.error("사진 저장 실패");
            e.printStackTrace();
            return ResponseEntity.badRequest().body("사진 저장 실패");
        }
        catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @MessageMapping("/channel/{channel}")
    @SendTo("/sub/{channel}")
    public MessageDTO sendMessage(@DestinationVariable("channel") String channelId, MessageDTO messageDTO){
        log.info("메세지를 받았습니다. " + messageDTO.getContent());
        log.info("현재 시간 : " + LocalDateTime.now());
        log.info("컨트롤러의 유저 닉네임 : " + messageDTO.getUserNickname());
        messageDTO.setSendTime();
        log.info(messageDTO.toString());
        log.info("channelId: "+channelId);

        chatService.saveMessage(channelId, messageDTO);
        return messageDTO;
    }
}
