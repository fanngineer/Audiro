package com.a402.audirochat.repository;

import com.a402.audirochat.entity.UserView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface UserViewRepository extends Repository<UserView, Long> {

    @Query(value = "select nickname from user_view where user_id=:user_id", nativeQuery = true)
    String findUserNicknameById(@Param("user_id") long id);

    @Query(value = "select profile_img from user_view where user_id=:user_id", nativeQuery = true)
    String findProfileImgById(@Param("user_id") long id);
}
