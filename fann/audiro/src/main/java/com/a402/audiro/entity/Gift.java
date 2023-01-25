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

    @Embedded
    private GiftFeedback giftFeedback;

    private boolean isManito;

    @Override
    public String toString() {
        return "Gift{" +
                "giftId=" + id +
                ", user=" + user +
                ", spot=" + spot +
                ", song=" + song +
                ", giftImg='" + giftImg + '\'' +
                ", isOpen=" + isOpen +
                ", giftTag=" + giftTag +
                ", regTime=" + regTime +
                ", giftFeedback=" + giftFeedback +
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

    public GiftFeedback getGiftFeedback() {
        return giftFeedback;
    }

    public void setGiftFeedback(GiftFeedback giftFeedback) {
        this.giftFeedback = giftFeedback;
    }

    public boolean isManito() {
        return isManito;
    }

    public void setManito(boolean manito) {
        isManito = manito;
    }
}
