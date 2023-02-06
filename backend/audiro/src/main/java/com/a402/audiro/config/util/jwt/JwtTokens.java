/*
 * jwt token과 refresh token을 저장할 객체
 * */
package com.a402.audiro.config.util.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class JwtTokens {

    private String accessToken;
    private String refreshToken;

}