package com.a402.audiro.controller;

import com.a402.audiro.dto.SongGiftListDTO;
import com.a402.audiro.dto.SongSearchDTO;
import com.a402.audiro.service.SongGiftListService;
import com.a402.audiro.service.SongService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/song/search")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Builder
@Slf4j
public class SongSearchController {

    private final SongService songListService;

    @GetMapping("/title")
    public ResponseEntity<?> getSongTitleList(@RequestParam String keyword){
        try{
            List<SongSearchDTO> songList = songListService.searchTitle(keyword);
            return ResponseEntity.ok().body(songList);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/singer")
    public ResponseEntity<?> getSingerList(@RequestParam String keyword){
        try{
            List<SongSearchDTO> songList = songListService.searchSinger(keyword);
            return ResponseEntity.ok().body(songList);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
