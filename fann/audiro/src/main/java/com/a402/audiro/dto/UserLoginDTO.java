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
    private String email;
    private String name;
    private String img;
}
