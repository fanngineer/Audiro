package com.a402.audiro.service;

import com.a402.audiro.dto.GiftThumbnailDTO;
import com.a402.audiro.dto.ManitoDTO;
import com.a402.audiro.entity.*;
import com.a402.audiro.repository.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class ManitoServiceImpl implements ManitoService{

    private final GiftRepository giftRepository;
    private final UserRepository userRepository;
    private final SongRepository songRepository;
    private final SpotRepository spotRepository;
    private final SongMetaRepository songMetaRepository;

    private final UserService userService;

    @Override
    public List<GiftThumbnailDTO> getManitoList(long spotId) {
        List<Gift> manito;
        List<GiftThumbnailDTO> manitoList;

        try{
            manito = giftRepository.findManitos(spotId);
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
            //현재 로그인된 유저
            long userId = userService.getUser().getId();
            log.info("마니또 등록한 사용자 ID : {}", userId);
            manitoDTO.setUserId(userId); //유저 정보 반영

            // 기존 마니토를 밀어냄
            Gift beforeManito = giftRepository.findById(manitoDTO.getBeforeManitoId());
            beforeManito.setManito(Boolean.FALSE);
            giftRepository.save(beforeManito);
            log.info("삭제된 마니또 : {}",beforeManito);

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
            log.info("등록될 마니또 : {}",manito);
            giftRepository.save(manito);
            log.info("마니또 저장 완료");

            // 노래 추천 횟수 수정
            SongMeta beforeManitoMeta = songMetaRepository.findBySongAndSpot(beforeManito.getSong(), beforeManito.getSpot());
            log.info("beforeManitoMeta : {}",beforeManitoMeta);
            SongMeta manitoMeta = songMetaRepository.findBySongAndSpot(manito.getSong(), manito.getSpot());
            log.info("manitoMeta : {}",manitoMeta);
            if(manitoMeta == null){
                manitoMeta = SongMeta.builder()
                        .song(song)
                        .spot(spot)
                        .updateTime(LocalDateTime.now())
                        .liked(0)
                        .build();
            }

            if(beforeManitoMeta.getId() != manitoMeta.getId()){
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


    @Override
    public void saveGiftImg(MultipartFile giftImg, ManitoDTO manitoDTO) throws IOException {

        try{
            String UPLOADED_FOLDER = "/home/ubuntu/app/S08P12A402/backend/gift_images/"+manitoDTO.getSpotId().toString();
            log.info("마니또 이미지 저장 시작");
            //해당 경로에 manito 이미지 저장
            byte[] bytes = giftImg.getBytes();
                //이미지 이름
            String imageName = giftImg.getOriginalFilename();
            log.info("파일명 : {}", imageName);
                //이미지 저장
            Path path = Paths.get(UPLOADED_FOLDER +"/"+imageName);
            Files.write(path, bytes);
            log.info("이미지 저장 완료. 이미지 경로 : {}", path);
            //마니또 이미지 경로 및 마니또 객체 정보 저장
            manitoDTO.setGiftImg("http://i8a402.p.ssafy.io/gift_images/"+manitoDTO.getSpotId().toString()+"/"+imageName);
            addManito(manitoDTO);
        }catch (Exception e){
            log.info("사진 저장 실패");
            throw e;
        }

    }
}
