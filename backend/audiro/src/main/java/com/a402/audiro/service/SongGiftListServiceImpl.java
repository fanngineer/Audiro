package com.a402.audiro.service;

import com.a402.audiro.dto.SongGiftListDTO;
import com.a402.audiro.entity.Song;
import com.a402.audiro.entity.SongMeta;
import com.a402.audiro.repository.GiftRepository;
import com.a402.audiro.repository.SongMetaRepository;
import com.a402.audiro.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SongGiftListServiceImpl implements SongGiftListService{

    private final SongRepository songRepository;
    private final SongMetaRepository songMetaRepository;
    private final GiftRepository giftRepository;

    @Override
    public SongGiftListDTO getSongGiftList(long songId, long spotId){

        List<String> gifts;
        Song song;
        SongMeta songMeta;
        SongGiftListDTO songGiftListDTO = null;

        try{
            song = songRepository.findById(songId);
            songMeta = songMetaRepository.findBySongIdAndSpotId(songId, spotId);
            gifts = giftRepository.findBySongId(songId, spotId);
        }catch (Exception e){
            log.error(e.getMessage());
            throw e;
        }

        log.warn("class :"+ gifts.getClass());
        log.warn(gifts.toString());

        return songGiftListDTO.builder()
                .id(songId)
                .songTitle(song.getSongTitle())
                .singer(song.getSinger())
                .songUrl(song.getSongUrl())
                .songLiked(songMeta.getLiked())
                .giftCnt(songMeta.getCnt())
                .giftList(gifts)
                .build();
    }
}
