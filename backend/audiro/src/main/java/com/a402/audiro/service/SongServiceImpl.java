package com.a402.audiro.service;

import com.a402.audiro.dto.SongSearchDTO;
import com.a402.audiro.entity.Song;
import com.a402.audiro.exception.SongNotExistException;
import com.a402.audiro.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService{

    private final SongRepository songRepository;

    public Song isValidSong(long id){
        Song song = songRepository.findById(id);

        if(song == null) throw new SongNotExistException();

        return song;
    }

    @Transactional
    public List<SongSearchDTO> searchTitle(String keyword){
        List<Song> songs = songRepository.findBySongTitleContaining(keyword);
        List<SongSearchDTO> songDTOList = new ArrayList<>();

        for(Song song : songs){
            songDTOList.add(this.convertEntityToDTO(song));
        }
        return songDTOList;
    }

    @Transactional
    public List<SongSearchDTO> searchSinger(String keyword){
        List<Song> songs = songRepository.findBySingerContaining(keyword);
        List<SongSearchDTO> songDTOList = new ArrayList<>();

        for(Song song : songs){
            songDTOList.add(this.convertEntityToDTO(song));
        }
        return songDTOList;
    }

    private SongSearchDTO convertEntityToDTO(Song song){
        return SongSearchDTO.builder()
                .song_id(song.getId())
                .song_title(song.getSongTitle())
                .singer(song.getSinger())
                .build();
    }
}
