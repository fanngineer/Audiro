package com.example.websocket.repository;

import com.example.websocket.dto.SendMessage;
import org.springframework.data.repository.CrudRepository;

public interface SendMessageRepository extends CrudRepository<SendMessage, String> {

}
