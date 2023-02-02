package com.a402.audiro.service;

import com.a402.audiro.dto.UserInfoDTO;
import com.a402.audiro.dto.UserLoginDTO;
import com.a402.audiro.entity.User;
import com.a402.audiro.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserInfoDTO selectUser(String id) {
        User userEntity;

        try {
            userEntity = userRepository.findById(id);
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw e;
        }
        return UserInfoDTO.builder()
                .id(userEntity.getId())
                .nickname(userEntity.getNickname())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .msg(userEntity.getMsg())
                .img(userEntity.getImg())
                .build();
    }

    @Override
    public void updateUserNickName(String newNickName) {
        try {
            //지금 로그인된 유저
            UserLoginDTO loginUser = (UserLoginDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String loginUserId = loginUser.getId();
            
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public void updateUserMsg(String newMsg) {
        try {

        }
        catch (Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public void updateUserImg(String newImg) {
        try {

        }
        catch (Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public List<UserInfoDTO> getUserList() {
        try {

        }
        catch (Exception e){
            log.error(e.getMessage());
            throw e;
        }
        return null;
    }
}
