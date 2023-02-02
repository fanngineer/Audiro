package com.a402.audiro.config.util.jwt;

import com.a402.audiro.dto.UserLoginDTO;
import com.a402.audiro.entity.User;
import com.a402.audiro.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {
    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String accessToken = ((HttpServletRequest)request).getHeader("Auth");

        System.out.println(accessToken);
        //요청을 받으면 들어온 토큰에 대해서 유효성 검증!!
        if (accessToken != null && jwtTokenService.verifyToken(accessToken)) {
            //토큰에서 지금 접속한 사용자 정보 꺼내기
            String id = jwtTokenService.getUserId(accessToken);
            String nickName = jwtTokenService.getUserNickName(accessToken);
            String role = jwtTokenService.getUserRole(accessToken); //토큰에 role을 담았는데, role이 변하는 기능을 넣으면 DB에서 조회한 값을 넣는게 맞는듯

            //객체에 담아서
            UserLoginDTO userLoginDTO = UserLoginDTO.builder()
                    .id(id)
                    .nickname(nickName)
                    .role(role)
                    .build();

            //SecurityContext에 권한과 함께 저장
            Authentication auth = getAuthentication(userLoginDTO, userLoginDTO.getRole());
            SecurityContextHolder.getContext().setAuthentication(auth);
            UserLoginDTO userNow = (UserLoginDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.out.println("현재 사용자 ID : " + userNow.getId() + ", 권한 : " + userNow.getRole() + ", 닉네임 : " + userNow.getNickname());
        }

        chain.doFilter(request, response);
    }

    public Authentication getAuthentication(UserLoginDTO member,String role) {
        return new UsernamePasswordAuthenticationToken(member, "",
                Arrays.asList(new SimpleGrantedAuthority(role)));
    }
}