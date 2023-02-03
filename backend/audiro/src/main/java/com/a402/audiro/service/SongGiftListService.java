package com.a402.audiro.service;

import com.a402.audiro.dto.SongGiftListDTO;

public interface SongGiftListService {
    SongGiftListDTO getSongGiftList(long songId, long spotId);
}
