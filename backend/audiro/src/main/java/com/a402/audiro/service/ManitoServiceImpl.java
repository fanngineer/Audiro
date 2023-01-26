package com.a402.audiro.service;

import com.a402.audiro.dto.GiftThumbnailDTO;
import com.a402.audiro.dto.ManitoDTO;
import com.a402.audiro.entity.*;
import com.a402.audiro.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ManitoServiceImpl implements ManitoService{

    private final GiftRepository giftRepository;
    private final UserRepository userRepository;
    private final SongRepository songRepository;
    private final SpotRepository spotRepository;
    private final SongMetaRepository songMetaRepository;

    @Override
    public List<GiftThumbnailDTO> getManitoList() {
        List<Gift> manito;
        List<GiftThumbnailDTO> manitoList;

        try{
            manito = giftRepository.findManitos();
            manitoList = manito.stream()
                    .map(m -> GiftThumbnailDTO.builder()
                            .id(m.getId())
                            .giftImg(m.getGiftImg())
                            .build())
                    .collect(Collectors.toList());
        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }

        return manitoList;
    }

    @Transactional
    @Override
    public void addManito(ManitoDTO manitoDTO) {
        try{
            // 기존 마니토를 밀어냄
            Gift beforeManito = giftRepository.findById(manitoDTO.getBeforeManitoId());
            beforeManito.setManito(Boolean.FALSE);

            // 새로운 마니토를 추가함
            User user = userRepository.findById(manitoDTO.getUserId());
            Song song = songRepository.findById(manitoDTO.getSongId());
            Spot spot = spotRepository.findById(manitoDTO.getSpotId());

            Gift manito = Gift.builder()
                    .user(user)
                    .song(song)
                    .spot(spot)
                    .giftImg(manitoDTO.getGiftImg())
                    .giftTag(manitoDTO.getGiftTag())
                    .isManito(Boolean.TRUE)
                    .build();
            giftRepository.save(manito);

            // 노래 추천 횟수 수정
            SongMeta beforeManitoMeta = songMetaRepository.findBySongAndSpot(beforeManito.getSong(), beforeManito.getSpot());
            SongMeta manitoMeta = songMetaRepository.findBySongAndSpot(manito.getSong(), manito.getSpot());

            if(manitoMeta == null){
                manitoMeta = SongMeta.builder()
                        .song(song)
                        .spot(spot)
                        .updateTime(LocalDateTime.now())
                        .liked(0)
                        .build();
            }

            if(beforeManito.getId() != manito.getId()){
                beforeManitoMeta.minusCnt();
                manitoMeta.plusCnt();
                songMetaRepository.save(beforeManitoMeta);
            }

            manitoMeta.setUpdateTime();

            songMetaRepository.save(manitoMeta);

        }catch (Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }
}
