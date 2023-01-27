package com.a402.audiro.config.auth;

// 로그인 요청 시 security가 로그인을 진행
// 로그인 진행 완료되면 security session 생성
// session에 들어가는 object는 Authenticaion 타입 객체
// User정보를 Authentication에 넣기 위해서 springSecurity의 userDetail 객체를 사용

import com.a402.audiro.entity.User;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class PrincipalDetails implements UserDetails{

    private User user;

    public PrincipalDetails(User user) {
        super();
        this.user = user;
    }

    @Override
    public String getPassword(){
        return null;
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    //만료 여부
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 특정 기간동안 로그인 안하면 휴면계정으로 설정할때 사용
        //user entity에 마지막 로그인 timestamp 저장
        return true;
    }


    //user의 권한을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collet = new ArrayList<>();
        collet.add(()->{ return user.getRole();});
        return collet;
    }
}
