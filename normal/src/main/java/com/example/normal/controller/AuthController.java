package com.example.normal.controller;

import com.example.normal.dto.RegisterDto;
import com.example.normal.dto.ResponseDto;
import com.example.normal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        userService.register(registerDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(new ResponseDto(true));
    }

    @GetMapping("/code")
    public String code() {
        return "code";
    }
}
