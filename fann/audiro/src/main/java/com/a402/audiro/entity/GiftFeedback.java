package com.a402.audiro.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GiftFeedback {

    @Column(name = "gift_feedback1")
    private int feed1;

    @Column(name = "gift_feedback2")
    private int feed2;

    @Column(name = "gift_feedback3")
    private int feed3;

    @Column(name = "gift_feedback4")
    private int feed4;

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
