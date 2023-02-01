package com.a402.audiro.config.oauth;

import com.a402.audiro.dto.UserLoginDTO;
import java.util.Map;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class UserRequestMapper {
    public UserLoginDTO toDto(OAuth2User oAuth2User) {
        Map attributes = oAuth2User.getAttributes();
        return UserLoginDTO.builder()
                .id((String)attributes.get("idOnly"))
                .email((String)attributes.get("email"))
                .name((String)attributes.get("name"))
                .img((String)attributes.get("picture"))
                .build();
    }
}
