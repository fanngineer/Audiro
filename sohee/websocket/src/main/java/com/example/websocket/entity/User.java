package com.example.websocket.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("user")
@Data
public class User {

    @Id
    private String id;
    private List<String> cid;

    @Builder
    public User(String id){
        this.id = id;
        this.cid = new ArrayList<>();
    }

    public void setCid(String cid){
        this.cid.add(cid);
    }
}
