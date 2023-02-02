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
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler{
    private final JwtTokenService jwtTokenService;
    private final UserRequestMapper userRequestMapper;
    private final ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        UserOAuth2DTO userOAuth2DTO = userRequestMapper.toDto(oAuth2User);

        //토큰에 담을 유저 정보
        String role = userOAuth2DTO.getRole();
        String nickName = userOAuth2DTO.getNickname();

        // 최초 로그인일때 회원가입 처리 할거야
        String nowId = userOAuth2DTO.getId();
        User userEntity = userRepository.findById(nowId);
        if(userEntity == null){
            //DB에 없으면 강제로 회원가입시키자
            userEntity = User.builder()
                    .id(nowId)
                    .nickname(nowId) //임시 닉네임?
                    .role("ROLE_USER")
                    .email(userOAuth2DTO.getEmail())
                    .name(userOAuth2DTO.getName())
                    .img(userOAuth2DTO.getImg())
                    .build();
            userRepository.save(userEntity);
        }else{
            role = userEntity.getRole();
            nickName = userEntity.getNickname();
        }

        //토큰발급
        JwtTokens token = jwtTokenService.generateToken(userOAuth2DTO.getId(), nickName, role);
        log.info("jwtToken : " + "{}", token);

        //토큰 넣기
        writeTokenResponse(response, token);
    }

    private void writeTokenResponse(HttpServletResponse response, JwtTokens token)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        response.addHeader("Auth", token.getAccessToken());
        response.addHeader("Refresh", token.getRefreshToken());
        response.setContentType("application/json;charset=UTF-8");

        PrintWriter writer = response.getWriter();
        writer.println(objectMapper.writeValueAsString(token));
        writer.flush();
    }
}
