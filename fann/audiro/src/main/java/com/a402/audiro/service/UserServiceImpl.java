package com.a402.audiro.service;

import com.a402.audiro.dto.UserInfoDTO;
import com.a402.audiro.dto.UserLoginDTO;
import com.a402.audiro.entity.User;
import com.a402.audiro.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserInfoDTO selectUser(String id) {
        User userEntity;
        try {
            userEntity = userRepository.findById(id);
        } catch (Exception e) {
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
            //현재 로그인된 유저와 아이디
            UserLoginDTO loginUser = (UserLoginDTO) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
            String loginUserId = loginUser.getId();
            log.info("사용자 {}의 닉네임을 {}로 변경 시작", loginUserId,newNickName);
            User userEntity = userRepository.findById(loginUserId);
            userEntity.setNickname(newNickName);
            userRepository.save(userEntity);
            log.info("닉네임 변경 완료");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public void updateUserMsg(String newMsg) {
        try {
            UserLoginDTO loginUser = (UserLoginDTO) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
            String loginUserId = loginUser.getId();
            log.info("사용자 {}의 상태메세지를 {}로 변경 시작", loginUserId,newMsg);
            User userEntity = userRepository.findById(loginUserId);
            userEntity.setMsg(newMsg);
            userRepository.save(userEntity);
            log.info("상태메세지 변경 완료");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public void updateUserImg(String newImg) {
        try {
            UserLoginDTO loginUser = (UserLoginDTO) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
            String loginUserId = loginUser.getId();
            log.info("사용자 {}의 사진을 변경 시작", loginUserId);
            User userEntity = userRepository.findById(loginUserId);
            userEntity.setNickname(newImg);
            userRepository.save(userEntity);
            log.info("사진 변경 완료");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public List<UserInfoDTO> getUserList() {
        List<User> users;
        List<UserInfoDTO> userInfoDTOs;
        try {
            users = userRepository.findAll();
            userInfoDTOs = users.stream()
                    .map(u -> UserInfoDTO.builder()
                            .id(u.getId())
                            .nickname(u.getNickname())
                            .name(u.getName())
                            .email(u.getEmail())
                            .img(u.getImg())
                            .msg(u.getMsg())
                            .role(u.getRole())
                            .build())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
        return userInfoDTOs;
    }
}
