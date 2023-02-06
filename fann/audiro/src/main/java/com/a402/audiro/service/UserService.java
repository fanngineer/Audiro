package com.a402.audiro.service;

import com.a402.audiro.dto.UserInfoDTO;
import java.util.List;

public interface UserService {
    //선택한 유저 정보 초회
    UserInfoDTO selectUser(String id);

    //본인 닉네임 변경
    void updateUserNickName(String newNickName);

    //본인 메세지 변경
    void updateUserMsg(String newMsg);

    //본인 닉네임 변경
    void updateUserImg(String newImg);

    //유저 목록 조회
    //쓸일 없는데 관리자 페이지나 뭐 할때 위해서 그냥 만듦
    List<UserInfoDTO> getUserList();
}
