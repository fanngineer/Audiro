package com.a402.audiro.controller;

import com.a402.audiro.dto.GiftThumbnailDTO;
import com.a402.audiro.dto.ManitoDTO;
import com.a402.audiro.service.ManitoService;
import com.a402.audiro.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/manito")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Builder
@Slf4j
public class ManitoController {

    private final ManitoService manitoService;
    private final UserService userService;

    @GetMapping("/{spotId}")
    public ResponseEntity<?> getManitoList(@PathVariable long spotId){
        try{
            log.info("마니또 리스트 요청을 받았습니다.");
            List<GiftThumbnailDTO> giftThumbnailDTOS = manitoService.getManitoList(spotId);
            return ResponseEntity.ok().body(giftThumbnailDTOS);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> addManito(@RequestParam(value = "giftImg") MultipartFile giftImg, @RequestParam(value ="manito", required = true) String manito){
        try{
            log.info("새로운 마니또가 들어왔습니다. 마니또 정보 : {}", manito);

            ObjectMapper objectMapper = new ObjectMapper();
            ManitoDTO manitoDTO = objectMapper.readValue(manito, ManitoDTO.class);
            log.info("새로운 마니또 DTO 맵핑 완료");
            //마니또 이미지 저장
            manitoService.saveGiftImg(giftImg, manitoDTO);
            return ResponseEntity.ok().body("Manito is uploaded");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
