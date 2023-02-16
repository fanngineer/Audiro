package com.a402.audiro.service;

import com.a402.audiro.dto.GiftDTO;
import com.a402.audiro.dto.GiftEmojiDTO;
import com.a402.audiro.dto.GiftThumbnailDTO;
import com.a402.audiro.entity.Gift;
import com.a402.audiro.entity.User;
import com.a402.audiro.exception.GiftNotExistException;
import com.a402.audiro.exception.StateNotValidException;
import com.a402.audiro.repository.GiftRepository;
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
    private final UserService userService;
    private final int OPEN = 1;
    private final int CLOSE = 0;

    @Override
    public List<GiftThumbnailDTO> getGiftList(String nickname){
        List<Gift> gift;
        List<GiftThumbnailDTO> giftDTOList;

        userService.isValidNickname(nickname);

        if(userService.isSameUser(nickname)){
            log.info("유저 본인의 페이지입니다.");
            gift = giftRepository.findByNickname(nickname);
            giftDTOList = gift.stream()
                    .map(g -> GiftThumbnailDTO.builder()
                            .id(g.getId())
                            .giftImg(g.getGiftImg())
                            .build())
                    .collect(Collectors.toList());
        }else{
            log.info("다른 유저의 피드입니다.");
            gift = giftRepository.findByNicknameAndIsOpen(nickname);
            giftDTOList = gift.stream()
                    .map(g -> GiftThumbnailDTO.builder()
                            .id(g.getId())
                            .giftImg(g.getGiftImg())
                            .build())
                    .collect(Collectors.toList());
        }

        return giftDTOList;
    }

    @Override
    public GiftDTO getGiftDetail(long giftId) {
        log.info("기프트를 조회합니다.");

        Gift gift = getGift(giftId);

        return GiftDTO.builder()
                .id(gift.getId())
                .giftImg(gift.getGiftImg())
                .song(gift.getSong().getSongTitle())
                .singer(gift.getSong().getSinger())
                .songUrl(gift.getSong().getSongUrl())
                .regDate(gift.getRegTime())
                .giftLike(gift.getLike())
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
        giftRepository.deleteById(giftId);
    }

    @Override
    public void addFeedbackCnt(long giftId, int idx) {
        Gift gift = getGift(giftId);
        switch (idx){
            case 1:
                gift.addFeed1();
                break;
            case 2:
                gift.addFeed2();
                break;
            case 3:
                gift.addFeed3();
                break;
            case 4:
                gift.addFeed4();
                break;
        }
        giftRepository.save(gift);
    }

    @Override
    public void addLike(long giftId) {
        Gift gift = getGift(giftId);
        gift.addLike();
        giftRepository.save(gift);
    }

    @Override
    public Gift getGift(long giftId) {
        Gift gift = giftRepository.findById(giftId);

        if(gift == null) throw new GiftNotExistException();
        return gift;
    }

    private void isValidState(int state){
        if(state != OPEN && state != CLOSE) throw new StateNotValidException();
    }

    @Override
    public void changeOpenState(long giftId, int state) {
        Gift gift = getGift(giftId);
        isValidState(state);

        gift.setOpen(state == OPEN);

        giftRepository.save(gift);
    }
}
