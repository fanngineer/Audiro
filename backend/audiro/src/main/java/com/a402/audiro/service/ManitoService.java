package com.a402.audiro.service;

import com.a402.audiro.dto.GiftThumbnailDTO;
import com.a402.audiro.dto.ManitoDTO;

import java.util.List;

public interface ManitoService {
    List<GiftThumbnailDTO> getManitoList();

    void addManito(ManitoDTO manitoDTO);
}
