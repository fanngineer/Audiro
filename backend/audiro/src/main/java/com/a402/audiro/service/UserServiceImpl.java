package com.a402.audiro.service;

import com.a402.audiro.dto.UserInfoDTO;
import com.a402.audiro.dto.UserLoginDTO;
import com.a402.audiro.entity.User;
import com.a402.audiro.exception.NickNameExistException;
import com.a402.audiro.exception.UserNotExistException;
import com.a402.audiro.repository.UserRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserInfoDTO selectUser(long id) {
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
            long loginUserId = loginUser.getId();

            //중복 검사
            User temp = userRepository.findByNickname(newNickName);
            if(temp != null){
                if(temp.getId()!=loginUserId){
                    log.info("닉네임 중복");
                    throw new NickNameExistException();
                }
            }

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
            long loginUserId = loginUser.getId();
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
    public void updateUserImg(MultipartFile newImg) throws IOException {
        try {
            //요청한 사람의 이미지
            User loginUser = getUser();
            long loginUserId = loginUser.getId();
            log.info("사용자 {}의 사진을 변경 시작", loginUserId);
            //이미지 저장 경로
            String UPLOADED_FOLDER = "/home/ubuntu/app/S08P12A402/backend/profile_images/";
            //이미지 확장자 추출
            String imageName = newImg.getOriginalFilename();
            String type = imageName.substring(imageName.lastIndexOf(".") + 1);
            //이미지 저장 시작
            byte[] bytes = newImg.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER +loginUserId+"."+type);
            Files.write(path, bytes);
            log.info("이미지 저장 완료. 이미지 경로 : {}", path);
            //DB에 파일 경로 업데이트
            loginUser.setImg(path.toString());
            userRepository.save(loginUser);
            log.info("사진 변경 완료");
        }catch (Exception e) {
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

    @Override
    public User getUser() {
        UserLoginDTO loginUser = (UserLoginDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        long loginUserId = loginUser.getId();
        User userEntity = userRepository.findById(loginUserId);

        return userEntity;
    }

    @Override
    public User getUser(long userId) {
        User user = userRepository.findById(userId);

        if(user == null) {
            log.info("존재하지 않는 유저입니다. : ", userId);
            throw new UserNotExistException();
        }
        return user;
    }

    @Override
    public boolean isSameUser(String nickname) {
        User user = getUser();

        return user.getNickname().equals(nickname);
    }

    @Override
    public void isValidNickname(String nickname) {
        User user = userRepository.findByNickname(nickname);
        if(user == null) throw new UserNotExistException();
    }

}
