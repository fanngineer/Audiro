package com.a402.audiro.controller;

import com.a402.audiro.dto.GiftDTO;
import com.a402.audiro.dto.GiftThumbnailDTO;
import com.a402.audiro.service.GiftService;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/gift")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Builder
@Slf4j
public class GiftController {

    private final GiftService giftService;

    @GetMapping
    public ResponseEntity<?> getGiftList(@RequestParam String nickname){
        log.info("현재 시간 : " + LocalDateTime.now());
        try{
            List<GiftThumbnailDTO> giftDTOList = giftService.getGiftList(nickname);
            return ResponseEntity.ok().body(giftDTOList);
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/detail")
    public ResponseEntity<?> getGiftDetail(@RequestParam long giftId){
        log.info("기프트 컨트롤러 호출");
        try{
            GiftDTO giftDTO = giftService.getGiftDetail(giftId);
            return ResponseEntity.ok().body(giftDTO);
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/feedback")
    public ResponseEntity<?> addFeedback(@RequestParam long giftId, @RequestParam int idx){
        try{
            giftService.addFeedbackCnt(giftId, idx);
            return ResponseEntity.ok().body("success");
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/like")
    public ResponseEntity<?> addLike(@RequestParam long giftId){
        try{
            giftService.addLike(giftId);
            return ResponseEntity.ok().body("좋아요를 1 증가시켰습니다.");
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/open")
    public ResponseEntity<?> changeOpenState(@RequestParam long giftId, @RequestParam int state){
        try{
            giftService.changeOpenState(giftId, state);
            return ResponseEntity.ok().body("success : 공개 여부 변경에 성공했습니다.");
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteGift(@RequestParam long giftId){
        try{
            giftService.deleteGift(giftId);
            return ResponseEntity.ok().body("Gift is deleted");
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
