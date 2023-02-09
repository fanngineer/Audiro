package com.a402.audiro.service;

import com.a402.audiro.dto.GiftThumbnailDTO;
import com.a402.audiro.dto.MusicmateDTO;
import com.a402.audiro.dto.MusicmateListDTO;
import com.a402.audiro.entity.User;
import com.a402.audiro.repository.MusicmateRepository;
import com.a402.audiro.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MusicmateServiceImpl implements MusicmateService{

    private final MusicmateRepository musicmateRepository;
    private final UserRepository userRepository;

    @Override
    public List<MusicmateListDTO> getMusicmateList(long userId) {
        List<Long> musicmates;
        List<MusicmateListDTO> musicmateList;
        User user;

        try {
            musicmates = musicmateRepository.findByUserId(userId);
            musicmateList = musicmates.stream()
                    .map(m -> MusicmateListDTO.builder()
                            .id(userRepository.findById(m).getId())
                            .nickname(userRepository.findById(m).getNickname())
                            .img(userRepository.findById(m).getImg())
                            .build())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }

        return musicmateList;
    }

}
