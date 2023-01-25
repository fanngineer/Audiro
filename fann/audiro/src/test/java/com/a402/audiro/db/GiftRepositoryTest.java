package com.a402.audiro.db;

import com.a402.audiro.entity.*;
import com.a402.audiro.repository.GiftRepository;
import com.a402.audiro.repository.SongRepository;
import com.a402.audiro.repository.SpotRepository;
import com.a402.audiro.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.List;

@SpringBootTest
public class GiftRepositoryTest {

    @Autowired
    GiftRepository giftRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SongRepository songRepository;

    @Autowired
    SpotRepository spotRepository;

    @Test
    @DisplayName("GiftRepository save 확인")
    void giftTableSaveCheck(){
        User user = userRepository.findById(2);
        Song song = songRepository.findById(1);
        Spot spot = spotRepository.findById(1);

        Gift gift = Gift.builder()
                .user(user)
                .spot(spot)
                .song(song)
                .giftImg("img path~~~")
                .build();

        giftRepository.save(gift);
    }

    @Test
    @DisplayName("GiftRepository findByGiftId 확인")
    void giftRepositoryFindByGiftIdCheck(){
        Gift gift = giftRepository.findById(1);
        Assertions.assertThat(gift.getUser().getName()).isEqualTo("gaok");
    }

    @Test
    @DisplayName("GiftFeedback 확인")
    void giftFeedbackCheck(){
        Gift gift = giftRepository.findById(1);
        Assertions.assertThat(gift.getGiftFeedback().getFeed1()).isEqualTo(0);

        gift.getGiftFeedback().setFeed1(1);
        giftRepository.save(gift);
    }

    @Test
    @DisplayName("GfitTag 정확한 값 넣기")
    void giftTagCheck(){
        Gift gift = giftRepository.findById(14);
        Assertions.assertThat(gift.getGiftTag()).isEqualTo(GiftTag.SUNNY);
    }

    @Test
    @DisplayName("GiftTag 이상한 값 넣기")
    void giftTagExceptionCheck(){
        org.junit.jupiter.api.Assertions.assertThrows(InvalidDataAccessApiUsageException.class, () -> {
            giftRepository.findById(15);
        });
    }

    @Test
    @DisplayName("FindByNickname 확인")
    void giftFindByNicknameCheck(){
        List<Gift> gift = giftRepository.findByNickname("gaok");
        Assertions.assertThat(gift.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("delete 확인")
    void giftDeleteById(){
        giftRepository.deleteById(13);
    }
}
