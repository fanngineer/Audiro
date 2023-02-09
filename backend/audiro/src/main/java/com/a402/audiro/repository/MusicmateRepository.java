package com.a402.audiro.repository;

import com.a402.audiro.entity.Musicmate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MusicmateRepository extends Repository<Musicmate, Long> {

    Musicmate findById(long id);

//    @Query(value = "select nickname from Musicmate inner join User on Musicmate.mate_id=User.user_id where Musicmate.is_mate=True and Musicmate.is_block=False and Musicmate.user_id=:userId",
//            nativeQuery = true)
//    List<String> findByUserId(@Param("userId") Long userId);

    @Query(value = "select mate_id from Musicmate inner join User on Musicmate.mate_id=User.user_id where Musicmate.is_mate=True and Musicmate.is_block=False and Musicmate.user_id=:userId",
            nativeQuery = true)
    List<Long> findByUserId(@Param("userId") Long userId);

}
