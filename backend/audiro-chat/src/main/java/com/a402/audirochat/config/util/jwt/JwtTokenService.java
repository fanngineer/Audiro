package com.a402.audirochat.config.util.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.util.Base64;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenService {

    private String secretKey = "token-secret-key should be long enough"; //jwt에 쓰일 secretkey

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }//Base64로 인코딩

    //토큰 생성 메서드

    //토큰 유효성 검증 메서드
    public boolean verifyToken(String token) {
        try {
            log.info("토큰 검증 시작");
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token).getBody();
            return true;
        } catch (ExpiredJwtException e) {
            log.warn("만료된 토큰 : {}", e.getMessage());
            throw e;
        } catch (JwtException e){
            log.warn("잘못된 토큰 : {}", e.getMessage());
            throw e;
        }
    }

    public long getUserId(String token) {
        return Integer.parseInt(
                Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("userId")
                        .toString());
    } //jwt토큰에서 userId 빼오는 메서드

    public String getUserNickName(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody()
                .get("nickName").toString();
    } //jwt토큰에서 userNickname 빼오는 메서드

    public String getUserRole(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("role")
                .toString();
    } //jwt토큰에서 role 빼오는 메서드

    public String getType(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody()
                .get("type").toString();
    } //jwt토큰에서 type 빼오는 메서드
}
