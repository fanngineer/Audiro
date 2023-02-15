package com.a402.audirochat.repository;

import com.a402.audirochat.entity.Channel;
import com.a402.audirochat.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChannelRepository channelRepository;

    @Test
    @DisplayName("User 2명 만들고 서로 채팅방 5개 들어가있기")
    void create2User(){
        int arr[] = {2, 1};
        for(int i=0; i<2; i++){
            User user = new User();
            user.setId(i+1);
            for(int j=0; j<5; j++){
                Optional<Channel> channel = channelRepository.findById("ch"+(j+1));
                user.addChannels(1, channel.get(), "user"+arr[i]);
            }
            userRepository.save(user);
        }
    }

    @Test
    @DisplayName("User를 만들어보자~~")
    void createUser(){
        User user = new User();
        user.setId(1);

        userRepository.save(user);
    }

    @Test
    @DisplayName("User의 채널을 찾아보자!")
    void readUser(){
        Optional<User> user = userRepository.findById(1l);
        System.out.println(user.toString());
    }

    @Test
    @DisplayName("User에 채널을 추가해 업데이트 하깅")
    void updateUser(){
        Optional<User> user = userRepository.findById(1l);
        Channel channel = new Channel();
        channel.setId("ch3");
        user.get().addChannels(1, channel, "sohee_friend2");

        userRepository.save(user.get());

        user = userRepository.findById(1l);
        Assertions.assertThat(user.get().getChannels().get(1).getChannelName()).isEqualTo("sohee_friend2");
    }

    @Test
    @DisplayName("User 삭제!!")
    void deleteUser(){
        userRepository.deleteById(1l);
        Assertions.assertThat(userRepository.findById(1l)).isEqualTo(Optional.empty());
    }
}
