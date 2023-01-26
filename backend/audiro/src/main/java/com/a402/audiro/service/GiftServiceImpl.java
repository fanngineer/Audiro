package com.a402.audiro.service;

import com.a402.audiro.dto.GiftDTO;
import com.a402.audiro.dto.GiftEmojiDTO;
import com.a402.audiro.dto.GiftThumbnailDTO;
import com.a402.audiro.entity.Gift;
import com.a402.audiro.repository.GiftRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class GiftServiceImpl implements GiftService{

    private final GiftRepository giftRepository;

    @Override
    public List<GiftThumbnailDTO> getGiftList(String nickname){
        List<Gift> gift;
        List<GiftThumbnailDTO> giftDTOList;

        try{
            gift = giftRepository.findByNickname(nickname);
            giftDTOList = gift.stream()
                    .map(g -> GiftThumbnailDTO.builder()
                            .id(g.getId())
                            .giftImg(g.getGiftImg())
                            .build())
                    .collect(Collectors.toList());
        }catch (Exception e){
            log.error(e.getMessage());
            throw e;
        }

        return giftDTOList;
    }

    @Override
    public GiftDTO getGiftDetail(long giftId) {
        Gift gift;

        try{
            gift = giftRepository.findById(giftId);
        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }

        return GiftDTO.builder()
                .id(gift.getId())
                .giftImg(gift.getGiftImg())
                .song(gift.getSong().getSongTitle())
                .singer(gift.getSong().getSinger())
                .songUrl(gift.getSong().getSongUrl())
                .regDate(gift.getRegTime())
                .emoji(GiftEmojiDTO.builder()
                        .emo1(gift.getFeed1())
                        .emo2(gift.getFeed2())
                        .emo3(gift.getFeed3())
                        .emo4(gift.getFeed4())
                        .build())
                .build();
    }

    @Override
    public void deleteGift(long giftId) {
        try{
            giftRepository.deleteById(giftId);
        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }

    @Transactional
    @Override
    public void updateFeedback(long giftId, int feedbackNum) {
        try{
            Gift gift = giftRepository.findById(giftId);
            switch (feedbackNum){
                case 1:
                    gift.plusFeed1();
                    break;
                case 2:
                    gift.plusFeed2();
                    break;
                case 3:
                    gift.plusFeed3();
                    break;
                case 4:
                    gift.plusFeed4();
                    break;
            }
            giftRepository.save(gift);
        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }
}
