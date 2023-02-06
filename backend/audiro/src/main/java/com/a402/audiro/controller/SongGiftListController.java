package com.a402.audiro.controller;

import com.a402.audiro.dto.SongGiftListDTO;
import com.a402.audiro.service.SongGiftListService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("song/gifts")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Builder
@Slf4j
public class SongGiftListController {
    private final SongGiftListService songGiftListService;

    @GetMapping
    public ResponseEntity<?> getSongGiftList(@RequestParam long songId, @RequestParam long spotId){
        try{
            SongGiftListDTO songGiftListDTO = songGiftListService.getSongGiftList(songId, spotId);
            return ResponseEntity.ok().body(songGiftListDTO);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
