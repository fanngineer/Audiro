package com.a402.audiro.service;

import com.a402.audiro.repository.MusicmateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MusicmateServiceImpl implements MusicmateService{

    private final MusicmateRepository musicmateRepository;

    @Override
    public List<String> getMusicmateList(long userId) {
        List<String> musicmates;

        try {
            musicmates = musicmateRepository.findByUserId(userId);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }

        return musicmates;
    }

}
