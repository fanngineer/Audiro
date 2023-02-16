/*
 * Spring Security 사용을 위한 Config 파일
 * */
package com.a402.audirochat.config;

import com.a402.audirochat.config.util.jwt.JwtFilter;
import com.a402.audirochat.config.util.jwt.JwtTokenService;
import com.a402.audirochat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity //Spring Security 필터가 Spring 필터체인에 등록
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
// 특정 주소 접근시 권한 및 인증을 위한 어노테이션(secured, pre~~) 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        // resources 모든 접근을 허용하는 설정을 해버리면
        // HttpSecurity 설정한 ADIM권한을 가진 사용자만 resources 접근가능한 설정을 무시해버린다.
        //스웨거 요청에 대해서 security 작동안함
        web.ignoring()
                .antMatchers("/ws-stomp","/swagger-ui/index.html","/swagger-ui.html","/", "/loginForm", "/v3/api-docs",  "/configuration/ui",
                        "/swagger-resources", "/configuration/security",
                        "/webjars/**","/swagger/**", "/");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.httpBasic().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);//토큰 기반 인증을 위해 세션 생성x

        http.authorizeRequests()
                .antMatchers("/","/swagger-resources/**","/swagger-ui/*","/swagger-ui.html","/chat",
                        "/api/swagger-ui.html")
                .permitAll() //메인페이지는 모든 사용자에게 가능하게
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") //인증뿐 아니라 권한이 있는 사람만
                .anyRequest().authenticated(); //나머지 요청에 대해서는 전부 사용자 인증
//                .addFilterBefore(new JwtExceptionFi)
//                .formLogin() //폼로그인을 쓰겠다. 다른 페이지로 갈때 로그인주소로 이동
//                .loginPage("/loginForm")
//                .usernameParameter("email") //식별을 위해 username을 entity에서 email으로 사용하므로 변경
//                .loginProcessingUrl("/loginProc") // </login> 호출 시 security가 로그인 진행해줌
//                .defaultSuccessUrl("/")//로그인 성공 시 메인페이지
//                .and()
        http.formLogin()//OAuth2 로그인 방식을 사용
                .loginPage("http://i8a402.p.ssafy.io:80/intro");//Oauth로그인 페이지 설정, 로그인 안되어있으면 기본 login으로 가는데 그 주소를 loginForm으로 바꿔줌
        //jwt 토큰 필터 추가
        http.addFilterBefore(new JwtFilter(jwtTokenService),
                UsernamePasswordAuthenticationFilter.class);
    }

    /*
     * 쿠키 기반 인가 Repository
     * 인가 응답을 연계 하고 검증할 때 사용.
     * */
}

