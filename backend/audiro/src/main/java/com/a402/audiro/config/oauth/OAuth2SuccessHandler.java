/*
 * 구글 등등에서 사용자가 로그인 성공시
 * - 사용자 정보를 DB조회
 * - 최초 로그인이면 회원가입
 * - DB에 있으면 업데이트
 * - JWT토큰 넣어주기
 * */

package com.a402.audiro.config.oauth;

import com.a402.audiro.config.util.jwt.JwtTokenService;
import com.a402.audiro.config.util.jwt.JwtTokens;
import com.a402.audiro.dto.UserOAuth2DTO;
import com.a402.audiro.entity.User;
import com.a402.audiro.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler{
    private final JwtTokenService jwtTokenService;
    private final OAuth2DTOMapper OAuth2DTOMapper;
    private final ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        UserOAuth2DTO userOAuth2DTO = OAuth2DTOMapper.toDto(oAuth2User);
        log.info("현재 로그인된 사용자 정보 : {}", userOAuth2DTO.toString());
        //토큰에 담을 유저 정보
        String token = userOAuth2DTO.getToken();

        // 최초 로그인일때 회원가입 처리 할거야
        User userEntity = userRepository.findByToken(token);
        long id;
        String role;
        String nickName;
        if(userEntity == null){
            role = "ROLE_USER";
            log.info("최초 로그인 입니다");
            //DB에 없으면 강제로 회원가입시키자
            userEntity = User.builder()
                    .role(role)
                    .email(userOAuth2DTO.getEmail())
                    .nickname("임시 닉네임")
                    .token(token)
                    .name(userOAuth2DTO.getName())
                    .img(userOAuth2DTO.getImg())
                    .build();
            userRepository.save(userEntity);
            //닉네임 넣어주기
            User userSaved = userRepository.findByToken(token);
            id = userSaved.getId();
            nickName = "사용자" + String.valueOf(id);
            userSaved.setNickname(nickName);
            userRepository.save(userSaved);
            log.info("회원가입 완료 : {}",userSaved.toString());
        }else{
            log.info("기존 회원입니다.");
            id = userEntity.getId();
            role = userEntity.getRole();
            nickName = userEntity.getNickname();
        }


        //토큰발급
        JwtTokens jwtTokens = jwtTokenService.generateToken(id, nickName, role);
        log.info("발급된 jwtToken : " + "{}", jwtTokens);

        //Response에 토큰 넣기
        writeTokenResponse(response, jwtTokens);
    }

    private void writeTokenResponse(HttpServletResponse response, JwtTokens token)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        response.addHeader("Auth", token.getAccessToken());
        response.addHeader("Refresh", token.getRefreshToken());
        response.setContentType("application/json;charset=UTF-8");
        log.info("Response에 담긴 jwtToken : " + "{}", token);
//
    }
}
