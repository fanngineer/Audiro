package com.a402.audirochat.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User implements Serializable {

    @Id
    private long Id;
//    private List<ChannelInfo> channels = new ArrayList<>();
    // 대화 상대 id, 채널 정보
    private Map<Long, ChannelInfo> channels = new HashMap<>();

    public User(long Id){
        this.Id = Id;
    }

    public void addChannels(long memberId, Channel channel, String channelName){
        this.channels.put(memberId, new ChannelInfo(channel, channelName));
    }
}
