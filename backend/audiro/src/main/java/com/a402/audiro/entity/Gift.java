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
public class Gift {

    @Id
    @Column(name = "gift_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "spot_id")
    private Spot spot;

    @ManyToOne
    @JoinColumn(name = "song_id")
    private Song song;

    private String giftImg;
    private boolean isOpen;

    @Enumerated(EnumType.STRING)
    private GiftTag giftTag;

    private LocalDateTime regTime;

    @Column(name = "gift_feedback1")
    private int feed1;

    @Column(name = "gift_feedback2")
    private int feed2;

    @Column(name = "gift_feedback3")
    private int feed3;

    @Column(name = "gift_feedback4")
    private int feed4;

    private boolean isManito;

    @Override
    public String toString() {
        return "Gift{" +
                "id=" + id +
                ", user=" + user +
                ", spot=" + spot +
                ", song=" + song +
                ", giftImg='" + giftImg + '\'' +
                ", isOpen=" + isOpen +
                ", giftTag=" + giftTag +
                ", regTime=" + regTime +
                ", feed1=" + feed1 +
                ", feed2=" + feed2 +
                ", feed3=" + feed3 +
                ", feed4=" + feed4 +
                ", isManito=" + isManito +
                '}';
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Spot getSpot() {
        return spot;
    }

    public Song getSong() {
        return song;
    }

    public String getGiftImg() {
        return giftImg;
    }

    public void setGiftImg(String giftImg) {
        this.giftImg = giftImg;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public GiftTag getGiftTag() {
        return giftTag;
    }

    public void setGiftTag(GiftTag giftTag) { this.giftTag = giftTag; }

    public LocalDateTime getRegTime() {
        return regTime;
    }

    public boolean isManito() {
        return isManito;
    }

    public void setManito(boolean manito) {
        isManito = manito;
    }

    public int getFeed1() {
        return feed1;
    }

    public void setFeed1(int feed1) {
        this.feed1 = feed1;
    }

    public int getFeed2() {
        return feed2;
    }

    public void setFeed2(int feed2) {
        this.feed2 = feed2;
    }

    public int getFeed3() {
        return feed3;
    }

    public void setFeed3(int feed3) {
        this.feed3 = feed3;
    }

    public int getFeed4() {
        return feed4;
    }

    public void setFeed4(int feed4) {
        this.feed4 = feed4;
    }
}
