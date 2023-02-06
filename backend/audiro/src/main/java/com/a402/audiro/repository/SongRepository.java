package com.a402.audiro.repository;

import com.a402.audiro.entity.Song;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface SongRepository extends Repository<Song, Long> {

    Song findById(long id);
    List<Song> findBySongTitle(String songTitle);
    List<Song> findBySinger(String singer);
    void save(Song song);
    void delete(Song song);
}
