package com.a402.audiro.entity;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {

    @Id
    @Column(name = "user_id")
    private String id;
    private String name;
    private String token;
    private String nickname;
    private String role; //ROLE_USER, ROLE_ADMIN >> 추후 구현을 위해 생성
    private String email;

    @Column(name = "profile_message")
    private String msg;

    @Column(name = "profile_img")
    private String img;


    public String getEmail() { return email; }

    public String getId() {
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

    public String getRole(){ return role; }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setImg(String img) { this.img = img; }

    public void setName(String name) { this.name = name; }

    public void setEmail(String email) { this.email = email; }

    public void setRole(String role){ this.role = role; }
}
