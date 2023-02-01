package com.a402.audiro.repository;

import com.a402.audiro.entity.User;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends Repository<User, Long> {

    User findById(long id);
    User findByNickname(String nickname);
    User findByToken(String token);
    User findByEmail(String email);
    @Transactional
    void save(User user);
    void delete(User user);
}
