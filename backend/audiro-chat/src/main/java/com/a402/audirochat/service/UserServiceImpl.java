package com.a402.audirochat.service;

import com.a402.audirochat.dto.UserLoginDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Override
    public UserLoginDTO getUser() {
        UserLoginDTO loginUser = (UserLoginDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return loginUser;
    }


}
