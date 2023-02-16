package com.a402.audiro.controller;

import com.a402.audiro.dto.PasswordDTO;
import com.a402.audiro.dto.PostcardDTO;
import com.a402.audiro.dto.PostcardDetailDTO;
import com.a402.audiro.exception.PasswordDuplicationException;
import com.a402.audiro.service.PostcardService;
import com.a402.audiro.service.SmsService;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/postcard")
@Slf4j
public class PostcardController {

    private final PostcardService postcardService;
    private final SmsService smsService;

    @GetMapping
    public ResponseEntity<?> getPostcardDetail(@RequestParam long postcardId){
        try{
            log.info("postcard를 조회합니다.");
            PostcardDetailDTO postcardDetailDTO = postcardService.getPostcardDetail(postcardId);
            return ResponseEntity.ok().body(postcardDetailDTO);

        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> sendPostcard(@RequestBody @Valid PostcardDTO postcardDTO){
        try{

            postcardService.savePostcard(postcardDTO);

            return ResponseEntity.ok().body("sucess: 메세지를 성공적으로 전송했습니다.");

        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/pw")
    public ResponseEntity<?> passwordValidationCheck(@RequestBody @Valid PasswordDTO passwordDTO){
        try{
            postcardService.isValidPassword(passwordDTO.getPasswd());
            return ResponseEntity.ok().body("success: 사용 가능한 암호입니다.");
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
