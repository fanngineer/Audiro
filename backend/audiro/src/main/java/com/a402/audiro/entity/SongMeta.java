package com.a402.audiro.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongMeta {

    @Id
    @Column(name = "song_meta_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "song_id")
    private Song song;

    @ManyToOne
    @JoinColumn(name = "spot_id")
    private Spot spot;

    @Column(name = "song_liked")
    private int liked;

    @Column(name = "gift_cnt")
    private int cnt;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Override
    public String toString() {
        return "SongMeta{" +
                "id=" + id +
                ", song=" + song +
                ", spot=" + spot +
                ", liked=" + liked +
                ", cnt=" + cnt +
                ", updateTime=" + updateTime +
                '}';
    }

    public long getId() {
        return id;
    }

    public Song getSong() {
        return song;
    }

    public Spot getSpot() {
        return spot;
    }

    public int getLiked() {
        return liked;
    }

    public int getCnt() {
        return cnt;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public void setUpdateTime() {
        this.updateTime = LocalDateTime.now();
    }

    public void plusCnt(){
        this.cnt++;
    }

    public void minusCnt(){
        this.cnt--;
        if(this.cnt < 0) this.cnt = 0;
    }
}
