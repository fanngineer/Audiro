package com.example.websocket.repository;

import com.example.websocket.entity.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, String> {

}
