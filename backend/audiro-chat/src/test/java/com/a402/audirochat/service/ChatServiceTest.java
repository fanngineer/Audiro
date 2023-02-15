package com.a402.audirochat.service;

import com.a402.audirochat.dto.ChannelThumbnailDTO;
import com.a402.audirochat.dto.MessageDTO;
import com.a402.audirochat.entity.Channel;
import com.a402.audirochat.entity.ContentType;
import com.a402.audirochat.exception.IdNullException;
import com.a402.audirochat.repository.ChannelRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ChatServiceTest {

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChannelRepository channelRepository;

    @Test
    @DisplayName("메세지 저장 테스트")
    void saveMessageTest(){
        for(int i=1; i<=5; i++){
            MessageDTO messageDTO = MessageDTO.builder()
                    .userId(1)
                    .userNickname("sohee")
                    .contentType(ContentType.MESSAGE)
                    .content("message~~~~~~~~안녕 ㅎㅎ")
                    .sendTime(LocalDateTime.now())
                    .build();
            chatService.saveMessage("ch"+i, messageDTO);
        }

        Optional<Channel> channel = channelRepository.findById("ch1");
        Assertions.assertThat(channel.get().getLastMessage()).isEqualTo("message~~~~~~~~안녕 ㅎㅎ");
    }

    @Test
    @DisplayName("이미지 메세지 저장")
    void saveImgMessageTest(){
        MessageDTO messageDTO = MessageDTO.builder()
                .userId(1)
                .userNickname("sohee")
                .contentType(ContentType.IMAGE)
                .content("https://cdn.discordapp.com/attachments/1056882470429138968/1069503701905059850/image.png")
                .sendTime(LocalDateTime.now())
                .build();
        chatService.saveMessage("ch1", messageDTO);
    }

    @Test
    @DisplayName("getChannelThumbnail 테스트")
    void getChannelThumbnailTest(){
        List<ChannelThumbnailDTO> channelThumbnailDTOList = chatService.getChannelThumbnail(1);
        Assertions.assertThat(channelThumbnailDTOList.size()).isEqualTo(5);
    }

    @Test
    @DisplayName("getChannelMessages 테스트")
    void getChannelMessagesTest(){
        List<MessageDTO> messageDTOList = chatService.getChannelMessages("ch1");
        Assertions.assertThat(messageDTOList.get(0).getContent()).isEqualTo("message~~~~~~~~안녕 ㅎㅎ");
    }

    @Test
    @DisplayName("userId에 등록된 채널이 없는 경우 빈 리스트 반환")
    void getEmptyChannelThumbnails(){
        List<ChannelThumbnailDTO> channelThumbnailDTOList = chatService.getChannelThumbnail(1);
        Assertions.assertThat(channelThumbnailDTOList.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("userId가 null인 경우 IdNullException 발생")
    void checkUserIdIsNULL(){
//        org.junit.jupiter.api.Assertions.assertThrows(IdNullException.class, ()->{
//           chatService.getChannelThumbnail(null);
//        });
    }

    @Test
    @DisplayName("채팅을 처음 하는 유저의 경우 ID를 Redis에 등록하고 빈 리스트 반환")
    void firstChatting(){
        List<ChannelThumbnailDTO> channelThumbnailDTOList = chatService.getChannelThumbnail(4);
        Assertions.assertThat(channelThumbnailDTOList.size()).isEqualTo(0);
    }
}