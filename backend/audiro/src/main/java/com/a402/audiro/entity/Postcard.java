package com.a402.audiro.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Postcard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "postcard_id")
    private long Id;

    @ManyToOne
    @JoinColumn(name = "send_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "song_id")
    private Song song;

    @ManyToOne
    @JoinColumn(name = "spot_id")
    private Spot spot;


    private String password;

    @Column(name = "postcard_img")
    private String postcardImg;

    @Column(name = "reg_time")
    private LocalDateTime regTime;

    @Override
    public String toString() {
        return "Postcard{" +
                "Id=" + Id +
                ", user=" + user +
                ", song=" + song +
                ", spot=" + spot +
                ", password='" + password + '\'' +
                ", postcardImg='" + postcardImg + '\'' +
                ", regTime=" + regTime +
                '}';
    }
}
