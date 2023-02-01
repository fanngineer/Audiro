package com.example.websocket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Channel {

    private String channelId;

    public static Channel createChannel(){
        Channel channel = new Channel();
        channel.channelId = UUID.randomUUID().toString();
        return channel;
    }

    public Channel(){
        this.channelId = UUID.randomUUID().toString();
    }

}
