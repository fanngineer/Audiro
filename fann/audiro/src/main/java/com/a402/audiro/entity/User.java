package com.a402.audiro.entity;

import javax.persistence.Entity;
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
    private String id;
    private String name;
    private String token;
    private String nickname;
    private String role; //ROLE_USER, ROLE_ADMIN >> 추후 구현을 위해 생성
    private String email;
    private String password; //시큐리티 로그인을 위해 그냥 넣어주기

    @Column(name = "profile_message")
    private String msg;

    @Column(name = "profile_img")
    private String img;

    @Override
    public String toString(){
        return String.format("User[id=%d, name='%s', token='%s', nickname='%s', email='%s']", id, name, token, nickname, email);
    }

    public String getEmail() { return email; }

    public String getUserId() {
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

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

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
