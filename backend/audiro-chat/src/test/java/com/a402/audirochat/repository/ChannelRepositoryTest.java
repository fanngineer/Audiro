package com.a402.audirochat.repository;

import com.a402.audirochat.entity.Channel;
import com.a402.audirochat.entity.ChannelMessage;
import java.util.Optional;

import com.a402.audirochat.entity.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ChannelRepositoryTest {

    @Autowired
    private ChannelRepository channelRepository;

    @Test
    @DisplayName("Channel 5개 생성")
    void createChannel(){
        for(int i=0; i<5; i++){
            Channel channel = new Channel();
            channel.setId("ch"+ (i+1));
            channelRepository.save(channel);
        }
    }

    @Test
    @DisplayName("Channel에 메세지를 추가해보자!")
    void addMessageToChannel(){
        Channel channel = new Channel();
        channel.setId("ch1");
        ChannelMessage message = new ChannelMessage(1, "soheenic", ContentType.MESSAGE, "hihi");
        channel.addChannelMessage(message);

        channelRepository.save(channel);
    }

    @Test
    @DisplayName("Channel에서 메세지를 가져와보자~")
    void getMessageFromChannel(){
        Optional<Channel> channel = channelRepository.findById("ch1");
        Assertions.assertThat(channel.get().getLastMessage()).isEqualTo("hihi");
    }

    @Test
    @DisplayName("Channel에 메세지를 추가해 update 하자")
    void updateChannel(){
        Optional<Channel> channel = channelRepository.findById("ch1");
        ChannelMessage message = new ChannelMessage(2, "soheenic2", ContentType.MESSAGE, "hihi2");
        channel.get().addChannelMessage(message);

        channelRepository.save(channel.get());
        Optional<Channel> channel2 = channelRepository.findById("ch1");
        Assertions.assertThat(channel2.get().getMessages().get(1).getUserId()).isEqualTo("sohee2");
    }

    @Test
    @DisplayName("Channel 정보를 삭제해보자!")
    void deleteChannel(){
        channelRepository.deleteById("ch1");
        Optional<Channel> channel = channelRepository.findById("ch1");
        Assertions.assertThat(channel).isEqualTo(Optional.empty());
    }
}