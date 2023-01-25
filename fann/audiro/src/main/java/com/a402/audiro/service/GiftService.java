package com.a402.audiro.service;

import com.a402.audiro.dto.GiftDTO;

import java.util.List;

public interface GiftService {
    List<GiftDTO> getGiftList(String nickname);

    GiftDTO getGiftDetail(long giftId);

    void deleteGift(long giftId);
}
