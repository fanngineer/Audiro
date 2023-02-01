package com.a402.audiro.controller;

import com.a402.audiro.dto.UserLoginDTO;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @GetMapping("/data")
    public ResponseEntity<String> userInfo(@PathVariable String id)
    {
        return new ResponseEntity<String>("성환", HttpStatus.OK);
    }
}
