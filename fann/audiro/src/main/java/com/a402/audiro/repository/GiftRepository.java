package com.a402.audiro.repository;

import com.a402.audiro.entity.Gift;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GiftRepository extends Repository<Gift, Long> {

    Gift findById(long id);
    List<Gift> findByUserId(long userId);

    @Query(value = "select * from gift join user on gift.user_id = user.user_id where user.nickname = :nickname",
            nativeQuery = true)
    List<Gift> findByNickname(@Param("nickname") String nickname);

    @Query(value = "select * from gift where gift.is_manito = TRUE",
            nativeQuery = true)
    List<Gift> findManitos();

    void save(Gift gift);
    void deleteById(long id);
}
