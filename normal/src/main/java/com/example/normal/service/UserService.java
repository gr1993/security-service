package com.example.normal.service;

import com.example.normal.dto.RegisterDto;
import com.example.normal.entity.Otp;
import com.example.normal.entity.Role;
import com.example.normal.entity.User;
import com.example.normal.repository.OtpRepository;
import com.example.normal.repository.UserRepository;
import com.example.normal.util.GenerateCodeUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(RegisterDto registerDto) {
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
    }

    public void renewOtp(String username) {
        String code = GenerateCodeUtil.generateCode();

        List<Otp> otpList = otpRepository.findByUsername(username);
        if (otpList.isEmpty()) {
            Otp otp = new Otp();
            otp.setUsername(username);
            otp.setCode(code);
            otpRepository.save(otp);
        } else {
            Otp otp = otpList.get(0);
            otp.setCode(code);
        }
    }
}
