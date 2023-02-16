package com.a402.audiro.service;

import com.a402.audiro.dto.MusicmateListDTO;
import com.a402.audiro.entity.Musicmate;
import com.a402.audiro.entity.User;

import java.util.List;

public interface MusicmateService {

    List<MusicmateListDTO> getMusicmateList(long userId);

    void followMusicmate(long mateId);

    void isAlreadyMusicmate(User user, User mate);

    Musicmate getMusicmate(User user, User mate);

    void unfollowMusicmate(long mateId);
}
