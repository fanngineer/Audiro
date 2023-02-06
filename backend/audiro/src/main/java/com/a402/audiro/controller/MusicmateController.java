package com.a402.audiro.controller;

import com.a402.audiro.dto.MusicmateDTO;
import com.a402.audiro.service.MusicmateService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("musicmate")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Builder
@Slf4j
public class MusicmateController {
    private final MusicmateService musicmateService;

    @GetMapping
    public ResponseEntity<?> getMusicmateList(@RequestParam Long userId){
        try{
            List<String> musicmateNickNameList = musicmateService.getMusicmateList(userId);
            return ResponseEntity.ok().body(musicmateNickNameList);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
