package com.example.websocket.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("chat")
@Data
public class Channel {

    @Id
    private String id;
    private List<String> uid;
    private List<String> mid;

    @Builder
    public Channel(String id){
        this.id = id;
        this.uid = new ArrayList<>();
        this.mid = new ArrayList<>();
    }

    public void setUid(String uid){this.uid.add(uid); }

    public void setMid(String mid){this.mid.add(mid); }
}
