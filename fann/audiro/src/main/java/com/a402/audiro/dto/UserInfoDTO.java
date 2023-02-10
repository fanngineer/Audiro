/*
* 회원 페이지에서 사용자 정보 불러올때 사용할 DTO
* */
package com.a402.audiro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserInfoDTO {
    private String id;
    private String nickname;
    private String name;
    private String email;
    private String msg;
    private String img;
    private String role;
}