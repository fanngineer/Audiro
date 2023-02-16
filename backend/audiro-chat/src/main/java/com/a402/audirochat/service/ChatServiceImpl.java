package com.a402.audirochat.service;

import com.a402.audirochat.dto.ChannelThumbnailDTO;
import com.a402.audirochat.dto.MessageDTO;
import com.a402.audirochat.entity.Channel;
import com.a402.audirochat.entity.ChannelMessage;
import com.a402.audirochat.entity.ContentType;
import com.a402.audirochat.entity.User;
import com.a402.audirochat.exception.ChannelNotExistException;
import com.a402.audirochat.repository.ChannelRepository;
import com.a402.audirochat.repository.UserNicknameRepository;
import com.a402.audirochat.repository.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{

    private final UserRepository userRepository;
    private final ChannelRepository channelRepository;
    private final UserNicknameRepository userNicknameRepository;
    private final String UPLOAD_DIR = "/home/ubuntu/app/S08P12A402/backend/audiro-chat/src/main/resources/";

    private Optional<User> getUser(long userId){
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()){
            User newUser = new User(userId);
            userRepository.save(newUser);
            user = userRepository.findById(userId);
        }

        log.info("유저 정보 : " + user.toString());
        return user;
    }

    private Optional<Channel> getChannel(String channelId){
        Optional<Channel> channel = channelRepository.findById(channelId);
        if(!channel.isPresent()){
            throw new ChannelNotExistException();
        }
        return channel;
    }

    private class TimeComparator implements Comparator<ChannelThumbnailDTO>{
        @Override
        public int compare(ChannelThumbnailDTO t1, ChannelThumbnailDTO t2){
            return t2.getLastMessageTime().compareTo(t1.getLastMessageTime());
        }

        @Override
        public boolean equals(Object o){
            return false;
        }
    }

    private boolean isExistChannel(User user1, User user2){
        if(user1.getChannels().containsKey(user2.getId())){
            log.info("이미 존재하는 채널입니다!");
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void saveMessage(String channelId, MessageDTO messageDTO) {
        log.info("save message의 닉네임 : " + messageDTO.getUserNickname());
    
        ChannelMessage message = ChannelMessage.builder()
                .userId(messageDTO.getUserId())
                .userNickname(messageDTO.getUserNickname())
                .contentType(messageDTO.getContentType())
                .content(messageDTO.getContent())
                .sendTime(messageDTO.getSendTime())
                .build();

        Optional<Channel> channel = getChannel(channelId);
        log.info("채널 정보 확인 : " + channel.get());

        channel.get().addChannelMessage(message);
        channelRepository.save(channel.get());
        log.info("메세지를 저장했습니다. " + message.toString());
    }

    @Override
    public void savePostcard(MultipartFile postcardImg, String channelId, MessageDTO messageDTO) throws IOException {
        // 이미지 저장
        byte[] bytes = postcardImg.getBytes();

        String imageName = postcardImg.getOriginalFilename();
        log.info("파일을 저장합니다 : "+ imageName);

        Path path = Paths.get(UPLOAD_DIR + "/" + imageName);
        log.info("저장 위치 : " + path.toString());
        Files.write(path, bytes);
        log.info("이미지 저장 완료");

        // 메세지 저장
        messageDTO.setContent(path.toString());
        saveMessage(channelId, messageDTO);
    }

    @Override
    public List<ChannelThumbnailDTO> getChannelThumbnail(long userId) {
        Optional<User> user = getUser(userId);
        List<ChannelThumbnailDTO> channelThumbnailDTOList = user.get().getChannels().values().stream()
                .map(c -> ChannelThumbnailDTO.builder()
                        .channelId(c.getChannel().getId())
                        .nickname(c.getChannelName())
                        .lastMessage(channelRepository.findById(c.getChannel().getId()).get().getLastMessage())
                        .lastMessageTime(channelRepository.findById(c.getChannel().getId()).get().getLastMessageTime())
                        .build())
                .collect(Collectors.toList());

        channelThumbnailDTOList.sort(new TimeComparator());

        return channelThumbnailDTOList;
    }

    @Override
    public List<MessageDTO> getChannelMessages(String channelId) {
        Optional<Channel> channels = getChannel(channelId);

        log.info("채널 메세지를 읽어옵니다. " + channelId);
        log.info("채널의 메세지 길이 : " + channels.get().getMessages().size());
        log.info("닉네임 : "+ channels.get().getMessages().get(0).getUserNickname());

        List<MessageDTO> messageDTOList = channels.get().getMessages().stream()
                .map(m -> MessageDTO.builder()
                        .userId(m.getUserId())
                        .userNickname(m.getUserNickname())
                        .contentType(m.getContentType())
                        .content(m.getContent())
                        .sendTime(m.getSendTime())
                        .build())
                .collect(Collectors.toList());
        
        log.info("하하하 : " + messageDTOList.get(messageDTOList.size() - 1).toString());
        
        return messageDTOList;
    }

    @Transactional
    @Override
    public String createChannel(MessageDTO messageDTO) {
        long userId1 = messageDTO.getUserId();
        long userId2 = messageDTO.getReceiverId();
        Optional<User> user1 = getUser(userId1);
        Optional<User> user2 = getUser(userId2);
        log.info("user 정보 찾기");

        Channel channel;

        if(isExistChannel(user1.get(), user2.get())){
             channel = user1.get().getChannels().get(userId2).getChannel();
        }else{
            channel = new Channel();
            log.info("채널 생성!!");

            String nickname2 = userNicknameRepository.findUserNicknameById(userId2);
            log.info("상대방 닉네임 확인");

            user1.get().addChannels(userId2, channel, nickname2);
            user2.get().addChannels(userId1, channel, messageDTO.getUserNickname());
            log.info("채널 인포 생성");

            userRepository.save(user1.get());
            userRepository.save(user2.get());
            log.info("유저 정보 업데이트");

            channelRepository.save(channel);
            log.info("채널 정보 저장");
        }

        return channel.getId();
    }
}