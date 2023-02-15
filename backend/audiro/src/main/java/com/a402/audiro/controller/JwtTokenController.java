package com.a402.audiro.controller;

import com.a402.audiro.config.util.jwt.JwtTokenService;
import com.a402.audiro.config.util.jwt.JwtTokens;
import com.a402.audiro.dto.UserLoginDTO;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdk.nashorn.internal.parser.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/token")
@RequiredArgsConstructor
public class JwtTokenController {

    private final JwtTokenService jwtTokenService;

    @GetMapping ("/refresh")
    private ResponseEntity refresh(HttpServletRequest request){
        String redirectUri = "http://i8a402.p.ssafy.io:80/login";
        log.info("토큰 Refresh 요청");
        String refreshToken = request.getHeader("Refresh");
        if (refreshToken == null) {
            //토큰이 없으면
            log.info("토큰이 없습니다.");
            return ResponseEntity.badRequest().body("토큰이 유효하지 않습니다");
        } else {
            log.info("refresh from request header : " + refreshToken);
            //토큰이 있으면 검증을 시도
            try {
                jwtTokenService.verifyToken(refreshToken);
                //토큰 검증이 통과했다면
                log.info("유효한 토큰입니다.");
                if (jwtTokenService.getType(refreshToken).equals("refresh")) {
                    //토큰이 엑세스 토큰인지 먼저 확인한 후,
                    //AccessToken 새로 생성하기
                    JwtTokens newTokens = jwtTokenService.refreshAccessToken(refreshToken);
                    return ResponseEntity.ok().header("Auth", newTokens.getAccessToken()).header("Refresh", newTokens.getRefreshToken()).body("토큰생성완료");
                }else{
                    //리프레쉬 토큰이 아닌 경우에는
                    log.warn("not-refresh-token");
                    return ResponseEntity.badRequest().body("토큰이 유효하지 않습니다");
                }
            } catch (ExpiredJwtException e) {
                //만료시에는 로그인 페이지로 데려다주고싶은데
                log.warn("refresh token expired");
                return ResponseEntity.status(HttpServletResponse.SC_MOVED_PERMANENTLY).header("Location",redirectUri).body("토큰이 유효하지 않습니다");
            } catch (JwtException e) {
                //토큰 검증에 실패했을때
                log.warn("wrong_token : key가 다릅니다.");
                return ResponseEntity.status(HttpServletResponse.SC_MOVED_PERMANENTLY).header("Location",redirectUri).body("토큰이 유효하지 않습니다");
            } catch (Exception e) {
                //사용자 정보 꺼내기에 실패한경우 >> secret key는 맞는데 토큰의 내용 형식이 이상한 경우
                log.warn("wrong_token : 위조된 토큰");
                log.warn("{}",e.getCause());
                return ResponseEntity.status(HttpServletResponse.SC_MOVED_PERMANENTLY).header("Location",redirectUri).body("토큰이 유효하지 않습니다");
            }
        }
    }
}
