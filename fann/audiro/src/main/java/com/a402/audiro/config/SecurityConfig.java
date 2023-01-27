/*
 * Spring Security 사용을 위한 Config 파일
 * */
package com.a402.audiro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity //Spring Security 필터가 Spring 필터체인에 등록
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // 특정 주소 접근시 권한 및 인증을 위한 어노테이션(secured, pre~~) 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated() //user는 인증이 필요
                //.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                //.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") //인증뿐 아니라 권한이 있는 사람만
                .anyRequest().permitAll() //나머지 요청에 대해서는 전부 허락
                .and()
                .formLogin() //다른 페이지로 갈때 로그인주소로 이동
                .loginPage("/loginForm")
                .usernameParameter("email") //식별을 위해 username을 entity에서 email으로 사용하므로 변경
                .passwordParameter("name") //비밀번호 대신에 name을 사용
                .loginProcessingUrl("/loginProc") // </login> 호출 시 security가 로그인 진행해줌
                .defaultSuccessUrl("/");    //로그인 성공 시 메인페이지
    }
}

