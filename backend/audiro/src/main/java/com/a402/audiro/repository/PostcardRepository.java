package com.a402.audiro.repository;

import com.a402.audiro.entity.Postcard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface PostcardRepository extends Repository<Postcard, Long> {
    @Query(value = "select * from Postcard where password = :password", nativeQuery = true)
    Postcard findByPasswrod(@Param("password") String pw);

    Postcard findById(long id);

    void save(Postcard postcard);
}
