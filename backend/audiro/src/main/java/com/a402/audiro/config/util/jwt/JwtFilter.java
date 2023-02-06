package com.a402.audiro.config.util.jwt;

import com.a402.audiro.dto.UserLoginDTO;
import com.a402.audiro.entity.User;
import com.a402.audiro.repository.UserRepository;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String accessToken = ((HttpServletRequest)request).getHeader("Auth");
        log.info("AccessToken from request header : " + accessToken);
        //요청을 받으면 들어온 토큰에 대해서 유효성 검증!!
        if (accessToken != null && jwtTokenService.verifyToken(accessToken)) {
            log.info("유효한 토큰입니다.");
            //토큰에서 지금 접속한 사용자 정보 꺼내기
            long id = jwtTokenService.getUserId(accessToken);
            String nickName = jwtTokenService.getUserNickName(accessToken);
            String role = jwtTokenService.getUserRole(accessToken); //토큰에 role을 담았는데, role이 변하는 기능을 넣으면 DB에서 조회한 값을 넣는게 맞는듯

            //객체에 담아서
            UserLoginDTO userLoginDTO = UserLoginDTO.builder()
                    .id(id)
                    .nickname(nickName)
                    .role(role)
                    .build();
            log.info("현재 사용자의 ID : {}, nickName : {}, role : {}",id,nickName,role);
            //SecurityContext에 권한과 함께 저장
            Authentication auth = getAuthentication(userLoginDTO, userLoginDTO.getRole());
            SecurityContextHolder.getContext().setAuthentication(auth);
            log.info("인증 권한 부여");
        }else{
            log.info("유효한 토큰이 없습니다.");
        }

        chain.doFilter(request, response);
    }

    public Authentication getAuthentication(UserLoginDTO member,String role) {
        return new UsernamePasswordAuthenticationToken(member, "",
                Arrays.asList(new SimpleGrantedAuthority(role)));
    }
}