/*
* View를 이용해서 테스트 하기위한 파일
* */

package com.a402.audiro.controller;

import com.a402.audiro.config.util.CustomBCryptPasswordEncoder;
import com.a402.audiro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomBCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/test/oauth/login")
    public @ResponseBody String loginTest(Authentication authentication){
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println(oAuth2User.getAttributes());
        return "소셜 로그인세션정보확인하기";
    }

    @GetMapping({"", "/"})
    public String index() {
        return "index";
    }

    @GetMapping("/user")
    public @ResponseBody String user(@AuthenticationPrincipal OAuth2User oAuth2User) {
        return "user";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin() {
        return "admin";
    }

    @GetMapping("/common")
    public @ResponseBody String manager() {
        return "모두가 볼 수 있는 페이지";
    }

    @GetMapping("/loginForm")
    public String login() {
        return "loginForm";
    }


//    @GetMapping("/joinForm")
//    public String join() {
//        return "joinForm";
//    }

//    @PostMapping("/join")
//    public String join(User user) {
//        System.out.println(user);
//        user.setRole("ROLE_USER");
//        //비밀번호 인코딩해서 넣어줘야됨
//        String rawPassword = "common-password";
//        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
//        user.setPassword(encPassword);
//        userRepository.save(user);
//        return "redirect:/loginForm";
//    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/info")
    public @ResponseBody String info(){
        return "개인정보";
    }

    @GetMapping("/afterLogin")
    public @ResponseBody String afterLogin(){
        return "로그인 완료";
    }
//    @GetMapping("/data")
//    public @ResponseBody String data(){ return "로그인해야 보이는 페이지"; }
}
