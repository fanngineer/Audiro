/*
 * 토큰과 CONTEXTHOLDER에서 현재 로그인된 사용자를 꺼내오기위한 DTO
 */
package com.a402.audiro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserLoginDTO {
    private String id;
    private String nickname;
    private String role;
}
