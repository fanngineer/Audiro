/*
* 구글로 부터 받은 Request 데이터 후처리 함수. 엑세스 토큰 + 사용자 정보
* */

package com.a402.audiro.config.oauth;


import com.a402.audiro.config.auth.CustomBCryptPasswordEncoder;
import com.a402.audiro.config.auth.PrincipalDetails;
import com.a402.audiro.config.oauth.provider.GoogleUserInfo;
import com.a402.audiro.config.oauth.provider.NaverUserInfo;
import com.a402.audiro.config.oauth.provider.OAuth2UserInfo;
import com.a402.audiro.entity.User;
import com.a402.audiro.repository.UserRepository;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class Oauth2Service extends DefaultOAuth2UserService {

    @Autowired
    private CustomBCryptPasswordEncoder bCryptPasswordEncoder; //비밀번호 생성하기 위해서

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //구글 로그인 클릭 > 로그인창> 로그인> code리턴(OAuth-client 라이브러리) > AccessToken 요청
        //UserRequest정보 > loadUser함수 호출 >구글에서 회원정보 받기

        OAuth2User oAuth2User = super.loadUser(userRequest);
        //Provider는 생략

        OAuth2UserInfo oAuth2UserInfo = null;
        if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")){
            oAuth2UserInfo = new NaverUserInfo((Map<String, Object>) oAuth2User.getAttributes().get("response"));
        }

        String email = oAuth2UserInfo.getEmail();
        String username = oAuth2UserInfo.getName();
        String providerId = oAuth2UserInfo.getProviderId();
        String password = bCryptPasswordEncoder.encode("common-password");//패스워드는 필요없는데 security 로그인에서 필요함으로 그냥 공통으로 인코딩해서 만들어줌
        String role = "ROLE_USER"; //role도 user로 설정

        //해당 이메일로 회원 가입있는지 찾아보고 로그인 시킬거야
        User userEntity = userRepository.findByEmail(email);

        if(userEntity == null){
            //DB에 없으면 강제로 회원가입시키자

            userEntity = User.builder()
                    .name(username)
                    .email(email)
                    .role(role)
                    .password(password)
                    .nickname("사용자"+providerId) //임시 닉네임?
                    .build();

            userRepository.save(userEntity);
        }else{

        }

        return new PrincipalDetails(userEntity, oAuth2User.getAttributes()); //세션 정보로 반환
    }
}
