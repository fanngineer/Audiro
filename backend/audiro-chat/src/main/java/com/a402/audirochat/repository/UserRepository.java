package com.a402.audirochat.repository;

import com.a402.audirochat.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
