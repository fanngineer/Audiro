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
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String token;
    private String nickname;

    @Column(name = "profile_message")
    private String msg;

    @Column(name = "profile_img")
    private String img;

    @Override
    public String toString(){
        return String.format("User[id=%d, name='%s', token='%s', nickname='%s']", id, name, token, nickname);
    }

    public long getUserId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public String getNickname() {
        return nickname;
    }

    public String getMsg() {
        return msg;
    }

    public String getImg() {
        return img;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
