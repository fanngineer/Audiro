package com.a402.audiro.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Song {

    @Id
    @Column(name = "song_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String songImg;
    private String songTitle;
    private String singer;
    private String songUrl;

    @Override
    public String toString() {
        return "Song{" +
                "userId=" + id +
                ", songImg='" + songImg + '\'' +
                ", songTitle='" + songTitle + '\'' +
                ", singer='" + singer + '\'' +
                ", songUrl='" + songUrl + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getSongImg() {
        return songImg;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public String getSinger() {
        return singer;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }
}
