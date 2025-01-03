package com.example.normal.security;

import com.example.normal.entity.Otp;
import com.example.normal.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OtpAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private OtpRepository otpRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String code = String.valueOf(authentication.getCredentials());

        List<Otp> otp = otpRepository.findByUsername(username);
        if (otp.isEmpty()) {
            throw new BadCredentialsException("Bad credentials");
        }

        Otp userOtp = otp.get(0);
        if (code.equals(userOtp.getCode())) {
            return new OtpAuthentication(username, code);
        }
        throw new BadCredentialsException("Bad credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OtpAuthentication.class.isAssignableFrom(authentication);
    }
}
