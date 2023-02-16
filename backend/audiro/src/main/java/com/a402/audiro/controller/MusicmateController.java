package com.a402.audiro.controller;

import com.a402.audiro.dto.MusicmateDTO;
import com.a402.audiro.dto.MusicmateListDTO;
import com.a402.audiro.service.MusicmateService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/musicmate")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Builder
@Slf4j
public class MusicmateController {
    private final MusicmateService musicmateService;

    @GetMapping
    public ResponseEntity<?> getMusicmateList(@RequestParam Long userId){
        try{
//            List<Long> musicmateNickNameList = musicmateService.getMusicmateList(userId);
            List<MusicmateListDTO> musicmateInfoList = musicmateService.getMusicmateList(userId);

            return ResponseEntity.ok().body(musicmateInfoList);
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/follow")
    public ResponseEntity<?> followMusicmate(@RequestParam("mateId") long mateId){
        try{
            log.info("해당 유저를 팔로우합니다. : " + mateId);
            musicmateService.followMusicmate(mateId);
            return ResponseEntity.ok().body("success : 뮤직메이트를 팔로우합니다.");
        }catch(Exception e){
            log.info(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> unfollowMusicmate(@RequestParam("mateId") long mateId){
        try{
            musicmateService.unfollowMusicmate(mateId);
            return ResponseEntity.ok().body("success : 뮤직메이트를 언팔로우합니다.");
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
