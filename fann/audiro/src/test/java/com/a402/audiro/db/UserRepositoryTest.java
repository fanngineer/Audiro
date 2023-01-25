package com.a402.audiro.db;

import com.a402.audiro.entity.User;
import com.a402.audiro.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("User 테이블 정보 저장 확인")
    void userTableSaveCheck(){
        User user = User.builder().name("sohee2").token("1029312347890132").nickname("sohee2 nickname").build();
        userRepository.save(user);
    }

    @Test
    @DisplayName("UserRepository findByNickname 확인")
    void userRepositoryFindByNicknameCheck(){
        User user = userRepository.findByNickname("sohee");
        Assertions.assertThat(user.getName()).isEqualTo("sohee");
    }

    @Test
    @DisplayName("UserRepository delete 확인")
    void userRepositoryDeleteCheck(){
        User user = userRepository.findByNickname("sohee2 nickname");
        userRepository.delete(user);
    }

    @Test
    @DisplayName("UserRepository update 확인")
    void userRepositoryUpdateCheck(){
        User user = userRepository.findByNickname("sohee");
        user.setMsg("안녕??");
        userRepository.save(user);
    }
}
