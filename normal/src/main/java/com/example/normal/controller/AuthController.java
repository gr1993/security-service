package com.example.normal.controller;

import com.example.normal.dto.RegisterDto;
import com.example.normal.dto.ResponseDto;
import com.example.normal.entity.Role;
import com.example.normal.entity.User;
import com.example.normal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        User user = new User();
        user.setUsername(registerDto.getUsername());
        String encodedPassword = passwordEncoder.encode(registerDto.getPassword());
        user.setPassword(encodedPassword);
        user.setAge(registerDto.getAge());

        Role role = new Role();
        role.setUsername(user.getUsername());
        role.setRole("ROLE_user");
        user.setRole(role);

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(new ResponseDto(true));
    }

    @GetMapping("/code")
    public String code() {
        return "code";
    }
}
