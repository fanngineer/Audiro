package com.a402.audiro.service;

import com.a402.audiro.dto.SongChartDTO;
import com.a402.audiro.entity.SongMeta;
import com.a402.audiro.repository.SongMetaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SongChartServiceImpl implements SongChartService{

    private final SongMetaRepository songMetaRepository;
    @Override
    public List<SongChartDTO> getSongListByGiftCnt(long spotId) {
        List<SongMeta> song;
        List<SongChartDTO> songDTOList;

        try{
            song = songMetaRepository.findBySpotIdByGiftCnt(spotId);
            songDTOList = song.stream()
                    .map(s -> SongChartDTO.builder()
                            .song_meta_id(s.getId())
                            .song_title(s.getSong().getSongTitle())
                            .singer(s.getSong().getSinger())
                            .song_url(s.getSong().getSongUrl())
                            .song_img(s.getSong().getSongImg())
                            .gift_cnt(s.getCnt())
                            .song_liked(s.getLiked())
                            .update_time(Timestamp.valueOf(s.getUpdateTime()).toLocalDateTime())
                            .build())
                    .collect(Collectors.toList());


        }catch (Exception e){
            log.error(e.getMessage());
            throw e;
        }

        return songDTOList;
    }

    @Override
    public List<SongChartDTO> getSongListByTime(long spotId) {
        List<SongMeta> song;
        List<SongChartDTO> songDTOList;

        try{
            song = songMetaRepository.findBySpotIdByTime(spotId);
            songDTOList = song.stream()
                    .map(s -> SongChartDTO.builder()
                            .song_meta_id(s.getId())
                            .song_title(s.getSong().getSongTitle())
                            .singer(s.getSong().getSinger())
                            .song_url(s.getSong().getSongUrl())
                            .song_img(s.getSong().getSongImg())
                            .gift_cnt(s.getCnt())
                            .song_liked(s.getLiked())
                            .update_time(Timestamp.valueOf(s.getUpdateTime()).toLocalDateTime())
                            .build())
                    .collect(Collectors.toList());


        }catch (Exception e){
            log.error(e.getMessage());
            throw e;
        }

        return songDTOList;
    }

    @Override
    public List<SongChartDTO> getSongListByRandom(long spotId) {
        List<SongMeta> song;
        List<SongChartDTO> songDTOList;

        try{
            song = songMetaRepository.findBySpotIdByRandom(spotId);
            songDTOList = song.stream()
                    .map(s -> SongChartDTO.builder()
                            .song_meta_id(s.getId())
                            .song_title(s.getSong().getSongTitle())
                            .singer(s.getSong().getSinger())
                            .song_url(s.getSong().getSongUrl())
                            .song_img(s.getSong().getSongImg())
                            .gift_cnt(s.getCnt())
                            .song_liked(s.getLiked())
                            .update_time(Timestamp.valueOf(s.getUpdateTime()).toLocalDateTime())
                            .build())
                    .collect(Collectors.toList());


        }catch (Exception e){
            log.error(e.getMessage());
            throw e;
        }

        return songDTOList;
    }

}
