package com.a402.audirochat.service;

import com.a402.audirochat.dto.UserLoginDTO;

public interface UserService {
    // 로그인중인 유저의 DB 정보 반환
    UserLoginDTO getUser();

}
