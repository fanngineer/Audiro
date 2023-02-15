package com.a402.audiro.service;

import com.a402.audiro.dto.GiftDTO;
import com.a402.audiro.dto.GiftThumbnailDTO;

import com.a402.audiro.entity.Gift;
import java.util.List;

public interface GiftService {
    List<GiftThumbnailDTO> getGiftList(String nickname);

    GiftDTO getGiftDetail(long giftId);

    void deleteGift(long giftId);

    void addFeedbackCnt(long giftId, int idx);

    void addLike(long giftId);

    Gift getGift(long giftId);

    void changeOpenState(long giftId, int state);
}
