package com.a402.audiro.service;

import com.a402.audiro.dto.GiftDTO;
import com.a402.audiro.dto.ManitoDTO;

import java.util.List;

public interface ManitoService {
    List<GiftDTO> getManitoList();

    void addManito(ManitoDTO manitoDTO);
}
