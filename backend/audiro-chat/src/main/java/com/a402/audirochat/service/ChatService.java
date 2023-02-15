package com.a402.audirochat.service;

import com.a402.audirochat.dto.ChannelThumbnailDTO;
import com.a402.audirochat.dto.MessageDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ChatService {

    void saveMessage(String channelId, MessageDTO messageDTO);

    void savePostcard(MultipartFile postcardImg, String channelId, MessageDTO messageDTO) throws IOException;

    List<ChannelThumbnailDTO> getChannelThumbnail(long userId);

    List<MessageDTO> getChannelMessages(String channelId);

    String createChannel(MessageDTO messageDTO);
}
