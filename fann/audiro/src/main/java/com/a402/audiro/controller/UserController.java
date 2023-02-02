package com.a402.audiro.controller;


import com.a402.audiro.dto.UserInfoDTO;
import com.a402.audiro.service.UserService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Builder
public class UserController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private final UserService userService;

    //선택한 유저 정보 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable String id){
        try{
            UserInfoDTO UserInfoDTO = userService.selectUser(id);
            return ResponseEntity.ok().body(UserInfoDTO);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //본인 닉네임 변경
    @PostMapping("/change-nickname")
    public ResponseEntity<String> changeUserNickName(String newNickName){
        try{
            userService.updateUserNickName(newNickName);
            return ResponseEntity.ok().body(SUCCESS);
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

