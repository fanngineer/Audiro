package com.a402.audiro.db;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SongMetaRepositoryTest {

    @Autowired
    private SongMetaRepository songMetaRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SpotRepository spotRepository;

    @Test
    @DisplayName("songId, spotId로 찾기 확인")
    public void findBySongIdAndSpotIdCheck(){
        SongMeta songMeta = songMetaRepository.findBySongIdAndSpotId(1, 1);
        Assertions.assertThat(songMeta.getId()).isEqualTo(0);
    }
}
