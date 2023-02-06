package com.a402.audiro.controller;

import com.a402.audiro.dto.SongChartDTO;
import com.a402.audiro.service.SongChartService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("song/chart")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Builder
@Slf4j
public class SongChartController {

    private final SongChartService songChartService;
    @GetMapping("/giftcnt")
    public ResponseEntity<?> getSongListByGiftCnt(@RequestParam long spotId){
        try{
            List<SongChartDTO> songChartByGiftCntList = songChartService.getSongListByGiftCnt(spotId);

            return ResponseEntity.ok().body(songChartByGiftCntList);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/updatetime")
    public ResponseEntity<?> getSongListByTime(@RequestParam long spotId){
        try{
            List<SongChartDTO> songChartByTimeList = songChartService.getSongListByTime(spotId);

            return ResponseEntity.ok().body(songChartByTimeList);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/random")
    public ResponseEntity<?> getSongListByRandom(@RequestParam long spotId){
        try{
            List<SongChartDTO> songChartByRandomList = songChartService.getSongListByRandom(spotId);

            return ResponseEntity.ok().body(songChartByRandomList);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
