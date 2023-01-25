package com.a402.audiro.controller;

import com.a402.audiro.dto.GiftDTO;
import com.a402.audiro.service.GiftService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gift")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Builder
public class GiftController {

    private final GiftService giftService;

    @GetMapping
    public ResponseEntity<?> getGiftList(@RequestParam String nickname){
        try{
            List<GiftDTO> giftDTOList = giftService.getGiftList(nickname);
            return ResponseEntity.ok().body(giftDTOList);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/detail")
    public ResponseEntity<?> getGiftDetail(@RequestParam long giftId){
        try{
            GiftDTO giftDTO = giftService.getGiftDetail(giftId);
            return ResponseEntity.ok().body(giftDTO);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteGift(@RequestParam long giftId){
        try{
            giftService.deleteGift(giftId);
            return ResponseEntity.ok().body("Gift is deleted");
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
