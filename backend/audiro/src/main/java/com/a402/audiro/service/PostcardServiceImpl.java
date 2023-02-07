package com.a402.audiro.service;

import com.a402.audiro.dto.PostcardDTO;
import com.a402.audiro.dto.PostcardDetailDTO;
import com.a402.audiro.entity.Postcard;
import com.a402.audiro.entity.Song;
import com.a402.audiro.entity.Spot;
import com.a402.audiro.entity.User;
import com.a402.audiro.exception.PasswordDuplicationException;
import com.a402.audiro.repository.PostcardRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostcardServiceImpl implements PostcardService{

    private final PostcardRepository postcardRepository;
    private final UserService userService;
    private final SongService songService;
    private final SpotService spotService;

    @Override
    public void isValidPassword(String password) {
        Postcard postcard = postcardRepository.findByPasswrod(password);

        if(postcard != null) throw new PasswordDuplicationException();
    }

    @Override
    public void savePostcard(PostcardDTO postcardDTO) {
        Song song = songService.isValidSong(postcardDTO.getSongId());
        Spot spot = spotService.isValidSpot(postcardDTO.getSpotId());
        isValidPassword(postcardDTO.getPasswd());

        User user = userService.getUser();

        Postcard postcard = Postcard.builder()
                .user(user)
                .song(song)
                .spot(spot)
                .password(postcardDTO.getPasswd())
                .postcardImg(postcardDTO.getPostcardImg())
                .regTime(LocalDateTime.now())
                .build();

        postcardRepository.save(postcard);
    }

    @Override
    public PostcardDetailDTO getPostcardDetail(long postcardId) {
        Postcard postcard = postcardRepository.findById(postcardId);
        log.info(postcard.toString());

        PostcardDetailDTO postcardDetailDTO = PostcardDetailDTO.builder()
                .id(postcard.getId())
                .postcardImg(postcard.getPostcardImg())
                .songTitle(postcard.getSong().getSongTitle())
                .singer(postcard.getSong().getSinger())
                .songUrl(postcard.getSong().getSongUrl())
                .regTime(postcard.getRegTime())
                .build();

        return postcardDetailDTO;
    }
}
