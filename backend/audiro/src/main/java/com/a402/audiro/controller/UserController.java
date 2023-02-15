package com.a402.audiro.controller;


import com.a402.audiro.config.util.jwt.JwtTokenService;
import com.a402.audiro.config.util.jwt.JwtTokens;
import com.a402.audiro.dto.UserInfoDTO;
import com.a402.audiro.dto.UserLoginDTO;
import com.a402.audiro.exception.NickNameExistException;
import com.a402.audiro.service.UserService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
@Builder
@CrossOrigin(value = {"*"}, exposedHeaders = {"Content-Disposition"})
public class UserController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private final UserService userService;
    private final JwtTokenService jwtTokenService;

    //선택한 유저 정보 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable long id){
        try{
            UserInfoDTO UserInfoDTO = userService.selectUser(id);
            return ResponseEntity.ok().body(UserInfoDTO);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //본인 닉네임 변경
    @PostMapping("/change-nickname")
    public ResponseEntity<String> changeUserNickName(@RequestParam String newNickName){
        try{
            userService.updateUserNickName(newNickName);
            //responseHeader에 새로운 토큰을 날려보내야함.
            UserLoginDTO userLoginDTO = (UserLoginDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            long id = userLoginDTO.getId();
            String role = userLoginDTO.getRole();
            JwtTokens newTokens = jwtTokenService.generateToken(id, newNickName, role);
            return ResponseEntity.ok().header("Auth",newTokens.getAccessToken()).header("Refresh",newTokens.getRefreshToken()).body(SUCCESS);

        }catch (NickNameExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //본인 메세지 변경
    @PostMapping("/change-msg")
    public ResponseEntity<String> changeUserMsg(String newMsg){
        try{
            userService.updateUserMsg(newMsg);
            return ResponseEntity.ok().body(SUCCESS);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //본인의 이미지 사진 변경
    //이미지 업로드는 로직이 어떻게 될까...
    @PostMapping("/change-img")
    public ResponseEntity<String> changeUserImg(String newImg){
        try{
            userService.updateUserImg(newImg);
            return ResponseEntity.ok().body(SUCCESS);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

