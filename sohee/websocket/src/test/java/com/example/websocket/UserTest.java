package com.example.websocket;

import com.example.websocket.entity.Channel;
import com.example.websocket.entity.Message;
import com.example.websocket.entity.User;
import com.example.websocket.repository.ChannelRepository;
import com.example.websocket.repository.MessageRepository;
import com.example.websocket.repository.UserRepository;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;

@SpringBootTest
public class UserTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    @DisplayName("저장 및 검색 확인")
    public void saveAndSearch(){
//        User user = User.builder().id("sohee").build();
//        user.setCid("1");
//        userRepository.save(user);
//
//        Optional<User> user2 = userRepository.findById("sohee");
//        System.out.println(user2.toString());

        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
        setOperations.add("soooh", "1");
        setOperations.add("soooh", "2");

    }

    @Test
    @DisplayName("채널")
    public void saveChannel(){
        Channel ch = Channel.builder().id("1").build();
        channelRepository.save(ch);
    }

    @Test
    @DisplayName("메세지")
    public void saveMessage(){
        Message msg = Message.builder().id("1").uid("sohee").cid("1").content("hihi").build();
        messageRepository.save(msg);
    }
}
