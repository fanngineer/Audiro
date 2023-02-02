package com.a402.audiro.config.util.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Service
public class JwtTokenService {
    private String secretKey = "token-secret-key should be long enough"; //jwt에 쓰일 secretkey

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }//Base64로 인코딩

    //토큰 생성 메서드
    public JwtTokens generateToken(String userId, String nickName,String role) {
        long tokenPeriod = 1000L * 60L * 10L; //인증토큰의 만료시간 10분
        long refreshPeriod = 1000L * 60L * 60L * 24L * 30L * 3L; //Refresh토큰 만료 3주

        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("role", role);
        claims.put("nickName",nickName);
        Date now = new Date();
        return new JwtTokens(
                Jwts.builder()
                        .setClaims(claims)
                        .setIssuedAt(now)
                        .setExpiration(new Date(now.getTime() + tokenPeriod))
                        .signWith(SignatureAlgorithm.HS256, secretKey)
                        .compact(),
                Jwts.builder()
                        .setClaims(claims)
                        .setIssuedAt(now)
                        .setExpiration(new Date(now.getTime() + refreshPeriod))
                        .signWith(SignatureAlgorithm.HS256, secretKey)
                        .compact());
    }

    //토큰 유효성 검증 메서드
    public boolean verifyToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return claims.getBody()
                    .getExpiration()
                    .after(new Date()); //시간 검증 메서드
        } catch (Exception e) {
            return false;
        }
    }

    public String getUserId(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    } //jwt토큰에서 userId 빼오는 메서드

    public String getUserNickName(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("nickName").toString();
    } //jwt토큰에서 userNickname 빼오는 메서드

    public String getUserRole(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("role").toString();
    } //jwt토큰에서 role 빼오는 메서드
}
