package com.a402.audiro.db;

import com.a402.audiro.repository.MusicmateRepository;
import org.assertj.core.api.Assertions;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

@SpringBootTest
public class MusicmateRepositoryTest {

    @Autowired
    MusicmateRepository musicmateRepository;

    @Test
    @DisplayName("userId로 찾기 확인")
    public void findByUserIdCheck(){
        List<String> nicknames = musicmateRepository.findByUserId(Long.valueOf(2));
        MatcherAssert.assertThat(nicknames, contains("sungwhan", "sohee", "hosung"));
    }

    @Test
    @DisplayName("userId로 이상한 값 넣으면 0이 반환 됨")
    void findByUserIdExceptionCheck(){
        List<String> nicknames = musicmateRepository.findByUserId(Long.valueOf(15));
        Assertions.assertThat(nicknames.size()).isEqualTo(0);
    }
}
