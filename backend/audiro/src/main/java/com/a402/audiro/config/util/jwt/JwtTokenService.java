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
    public String generateAccessJwt(long userId, String nickName, String role) {
        long tokenPeriod = 1000L * 60L * 100L; //인증토큰의 만료시간 100분
        Claims claims = Jwts.claims();
        claims.put("role", role);
        claims.put("userId",userId);;
        Date now = new Date();
        claims.put("nickName", nickName);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenPeriod))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
    public String generateRefreshJwt(long userId, String nickName, String role) {
        long refreshPeriod = 1000L * 60L * 60L * 24L * 30L * 3L; //Refresh토큰 만료 3주
        Claims claims = Jwts.claims();
        claims.put("userId",userId);
        claims.put("role", role);
        Date now = new Date();
        claims.put("nickName", nickName);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshPeriod))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }


    public JwtTokens generateToken(long userId, String nickName, String role) {

        return new JwtTokens(
                generateAccessJwt(userId, nickName, role),
                generateRefreshJwt(userId, nickName,role));
    }

    //토큰 유효성 검증 메서드
    public boolean verifyToken(String token) {
        try {
            log.info("토큰 검증 시작");
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

    //토큰 refresh 메서드
    public String refreshAccessToken(String refreshToken){
        //리프레쉬 토큰에 담긴 사용자 정보
        long id = getUserId(refreshToken);
        String nickName = getUserNickName(refreshToken);
        String role = getUserRole(refreshToken);
        return generateAccessJwt(id, nickName, role);
    }

    public long getUserId(String token) {
        return Integer.parseInt(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("userId").toString());
    } //jwt토큰에서 userId 빼오는 메서드

    public String getUserNickName(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody()
                .get("nickName").toString();
    } //jwt토큰에서 userNickname 빼오는 메서드

    public String getUserRole(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("role")
                .toString();
    } //jwt토큰에서 role 빼오는 메서드
}
