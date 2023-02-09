package com.a402.audiro.service;

import com.a402.audiro.dto.MusicmateListDTO;

import java.util.List;

public interface MusicmateService {

    List<MusicmateListDTO> getMusicmateList(long userId);
}
