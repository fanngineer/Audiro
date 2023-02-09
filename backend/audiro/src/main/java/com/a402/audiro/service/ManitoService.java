package com.a402.audiro.service;

import com.a402.audiro.dto.GiftThumbnailDTO;
import com.a402.audiro.dto.ManitoDTO;

import java.io.IOException;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

public interface ManitoService {
    List<GiftThumbnailDTO> getManitoList(long spotId);

    @Transactional
    void addManito(ManitoDTO manitoDTO);

    void saveGiftImg(MultipartFile giftImg, ManitoDTO manito) throws IOException;
}
