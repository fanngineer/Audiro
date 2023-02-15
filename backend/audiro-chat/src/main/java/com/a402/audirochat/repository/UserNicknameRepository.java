package com.a402.audirochat.repository;

import com.a402.audirochat.entity.UserNickname;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface UserNicknameRepository extends Repository<UserNickname, Long> {

    @Query(value = "select nickname from user_nickname where user_id=:user_id", nativeQuery = true)
    String findUserNicknameById(@Param("user_id") long id);
}
