/*
* Authenrication를 만들어서 session에 넣어주는 역할
* */

package com.a402.audiro.config.auth;

import com.a402.audiro.entity.User;
import com.a402.audiro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//security설정에서 /login 요청이 오면 자동으로 userDetailsService 타입으로 IoC 되어있는 함수가 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userEntity = userRepository.findByEmail(email);
        if(userEntity != null){
            return new PrincipalDetails(userEntity);
        }
        return null;
    }
}
