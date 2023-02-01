/*
* 비밀번호를 인코딩 하기 위한 컴포넌트, 순환참조때문에 따로 설계. 비밀번호는 사실 공통이라 필요없는데 시큐리티 인증과정에 넣으려고 그냥 만듦.
* */

package com.a402.audiro.config.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class CustomBCryptPasswordEncoder extends BCryptPasswordEncoder {

}
