/*
 * Spring Security 사용을 위한 Config 파일
 * */
package com.a402.audiro.config;

import com.a402.audiro.config.oauth.OAuth2Service;
import com.a402.audiro.config.oauth.OAuth2SuccessHandler;
import com.a402.audiro.config.util.jwt.JwtFilter;
import com.a402.audiro.config.util.jwt.JwtTokenService;
import com.a402.audiro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity //Spring Security 필터가 Spring 필터체인에 등록
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // 특정 주소 접근시 권한 및 인증을 위한 어노테이션(secured, pre~~) 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private OAuth2Service oauth2Service;
    @Autowired
    private OAuth2SuccessHandler oAuth2SuccessHandler;
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//토큰 기반 인증을 위해 세션 생성x
//                .and()
//                .authorizeRequests()
        http.authorizeRequests()
                .antMatchers("/","/loginForm","/exception/**","/common","/swagger-ui.html").permitAll() //메인페이지는 모든 사용자에게 가능하게
                //.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                //.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") //인증뿐 아니라 권한이 있는 사람만
                .anyRequest().authenticated(); //나머지 요청에 대해서는 전부 사용자 인증
//                .addFilterBefore(new JwtExceptionFi)
//                .formLogin() //폼로그인을 쓰겠다. 다른 페이지로 갈때 로그인주소로 이동
//                .loginPage("/loginForm")
//                .usernameParameter("email") //식별을 위해 username을 entity에서 email으로 사용하므로 변경
//                .loginProcessingUrl("/loginProc") // </login> 호출 시 security가 로그인 진행해줌
//                .defaultSuccessUrl("/")//로그인 성공 시 메인페이지
//                .and()
        http.oauth2Login()//OAuth2 로그인 방식을 사용
            .loginPage("/loginForm") //Oauth로그인 페이지 설정, 로그인 안되어있으면 기본 login으로 가는데 그 주소를 loginForm으로 바꿔줌
            .userInfoEndpoint() //로그인된 사용자 정보
            .userService(oauth2Service) //로그인 후처리, 도메인 마다 다르게 넘어온 정보를 가공된 user를 반환
            .and()
            .successHandler(oAuth2SuccessHandler); //로그인 성공 시에 리디렉션 및 DB에 저장

        //jwt 토큰 필터 추가
        http.addFilterBefore(new JwtFilter(jwtTokenService, userRepository), UsernamePasswordAuthenticationFilter.class);
    }
}

