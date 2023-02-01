package com.a402.audiro.config.util.jwt;

import com.a402.audiro.dto.UserLoginDTO;
import com.a402.audiro.entity.User;
import com.a402.audiro.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
    private UserRepository userRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = ((HttpServletRequest)request).getHeader("Auth");

        if (token != null && jwtTokenService.verifyToken(token)) {
            String id = jwtTokenService.getUserId(token);
            User user = userRepository.findById(id);

            UserLoginDTO userLoginDTO = UserLoginDTO.builder()
                    .id(id)
                    .nickname(user.getNickname())
                    .name(user.getName())
                    .role(user.getRole())
                    .email(user.getEmail())
                    .img(user.getImg()).build();

            Authentication auth = getAuthentication(userLoginDTO, userLoginDTO.getRole());
            SecurityContextHolder.getContext().setAuthentication(auth);

            System.out.println("현재 사용자 ID : " + userLoginDTO.getId() + "이름 : " +userLoginDTO.getName());
        }

        chain.doFilter(request, response);
    }

    public Authentication getAuthentication(UserLoginDTO member,String role) {
        return new UsernamePasswordAuthenticationToken(member, "",
                Arrays.asList(new SimpleGrantedAuthority(role)));
    }
}