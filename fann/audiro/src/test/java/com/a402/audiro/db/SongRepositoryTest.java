package com.a402.audiro.db;

import com.a402.audiro.entity.Song;
import com.a402.audiro.repository.SongRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class SongRepositoryTest {

    @Autowired
    SongRepository songRepository;

    @Test
    @DisplayName("Song 테이블 정보 저장 확인")
    void songTableSaveCheck(){
        Song song = Song.builder().songImg("song_cover_img_path")
                .songTitle("hype boy")
                .singer("New Jeans")
                .songUrl("http://yutube...hype-boy...")
                .build();
        songRepository.save(song);
    }

    @Test
    @DisplayName("SongRepository findById 확인")
    void songRepositoryFindBySongId(){
        Song song = songRepository.findById(1);
        Assertions.assertThat(song.getSongTitle()).isEqualTo("Ditto");
    }

    @Test
    @DisplayName("SongRepository findBySinger 확인")
    void songRepositoryFindBySinger(){
        List<Song> songs = songRepository.findBySinger("New Jeans");
        Assertions.assertThat(songs.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("SongRepository update 확인")
    void songRepositoryUpdateCheck(){
        Song song = songRepository.findById(11);
        song.setSongUrl("바뀐 url");
        songRepository.save(song);
    }

    @Test
    @DisplayName("SongRepository delete 확인")
    void songRepositoryDeleteCheck(){
        Song song = songRepository.findById(11);
        songRepository.delete(song);
    }

    @Test
    @DisplayName("null 반환 확인")
    void nullExceptionCheci(){
        Song song = songRepository.findById(111);
    }
}
