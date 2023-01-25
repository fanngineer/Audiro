package com.a402.audiro.db;

import com.a402.audiro.entity.Song;
import com.a402.audiro.entity.SongMeta;
import com.a402.audiro.entity.Spot;
import com.a402.audiro.repository.SongMetaRepository;
import com.a402.audiro.repository.SongRepository;
import com.a402.audiro.repository.SpotRepository;
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
