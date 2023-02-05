/*
 * 소셜 로그인으로 사용자 정보를 받아와서 DB에 넣을때 사용할 DTO
 * */
package com.a402.audiro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class UserOAuth2DTO {
    private String id;
    private String nickname;
    private String name;
    private String token;
    private String email;
    private String img;
    private String role;
}
