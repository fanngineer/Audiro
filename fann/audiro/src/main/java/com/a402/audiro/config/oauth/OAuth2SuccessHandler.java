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
import com.a402.audiro.dto.UserLoginDTO;
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
        UserLoginDTO userLoginDTO = userRequestMapper.toDto(oAuth2User);

        // 최초 로그인일때 회원가입 처리 할거야
        String nowId = userLoginDTO.getId();
        User userEntity = userRepository.findByEmail(nowId);
        if(userEntity == null){
            //DB에 없으면 강제로 회원가입시키자
            userEntity = User.builder()
                    .id(nowId)
                    .nickname(nowId) //임시 닉네임?
                    .role("ROLE")
                    .email(userLoginDTO.getEmail())
                    .name(userLoginDTO.getName())
                    .img(userLoginDTO.getImg())
                    .build();
            userRepository.save(userEntity);
        }else{

        }

        //토큰발급
        JwtTokens token = jwtTokenService.generateToken(userLoginDTO.getId(), "USER");
        log.info("jwtToken : " + "{}", token);

        //토큰 넣기
        writeTokenResponse(response, token);
    }

    private void writeTokenResponse(HttpServletResponse response, JwtTokens token)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        response.addHeader("Auth", token.getToken());
        response.addHeader("Refresh", token.getRefreshToken());
        response.setContentType("application/json;charset=UTF-8");

        PrintWriter writer = response.getWriter();
        writer.println(objectMapper.writeValueAsString(token));
        writer.flush();
    }
}
