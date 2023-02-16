package com.a402.audirochat.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_VIEW")
@Getter
public class UserView {

    @Id
    @Column(name = "user_id")
    private long id;

    private String nickname;

    @Column(name = "profile_img")
    private String profileImg;
}
