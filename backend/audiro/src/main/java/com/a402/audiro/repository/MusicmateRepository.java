package com.a402.audiro.repository;

import com.a402.audiro.entity.Musicmate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MusicmateRepository extends Repository<Musicmate, Long> {

    Musicmate findById(long id);

    @Query(value = "select nickname from musicmate inner join user on musicmate.mate_id=user.user_id where musicmate.is_mate=True and musicmate.is_block=False and musicmate.user_id=:userId",
            nativeQuery = true)
    List<String> findByUserId(@Param("userId") Long userId);
}
