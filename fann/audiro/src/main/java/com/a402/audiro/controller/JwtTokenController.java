package com.a402.audiro.controller;

import com.a402.audiro.config.util.jwt.JwtTokenService;
import com.a402.audiro.dto.UserLoginDTO;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/token")
public class JwtTokenController {

    @Autowired
    private JwtTokenService jwtTokenService;

    @PostMapping("/refresh")
    private ResponseEntity refresh(ServletRequest request){
        log.info("토큰 Refresh 요청");
        String refreshToken = ((HttpServletRequest)request).getHeader("Refresh");
        log.info("refresh from request header : " + refreshToken);
        //요청을 받으면 들어온 토큰에 대해서 유효성 검증!!
        if (refreshToken != null && jwtTokenService.verifyToken(refreshToken)) {
            log.info("유효한 Refresh 토큰입니다.");
            //AccessToken 새로 생성하기
            String newAccessToken = jwtTokenService.refreshAccessToken(refreshToken);
            log.info("AccessToken Refresh완료");
            //SecurityContext에 권한과 함께 저장
            return ResponseEntity.ok().header("Refresh",newAccessToken).body("토큰생성완료");
        }else{
            log.info("유효한 Refresh 토큰이 없습니다.");
            return ResponseEntity.badRequest().body("토큰이 유효하지 않습니다");
        }
    }
}
