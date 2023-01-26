package com.a402.audiro.service;

import com.a402.audiro.dto.GiftDTO;
import com.a402.audiro.dto.GiftThumbnailDTO;

import java.util.List;

public interface GiftService {
    List<GiftThumbnailDTO> getGiftList(String nickname);

    GiftDTO getGiftDetail(long giftId);

    void deleteGift(long giftId);
}
