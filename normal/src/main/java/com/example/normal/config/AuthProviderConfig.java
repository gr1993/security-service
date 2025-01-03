package com.example.normal.config;

import com.example.normal.security.*;
import com.example.normal.security.strategy.AuthStrategy;
import com.example.normal.security.strategy.JwtAuthStrategy;
import com.example.normal.security.strategy.SessionAuthStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class AuthProviderConfig {

    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    @Autowired
    private OtpAuthenticationProvider otpAuthenticationProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.authenticationProvider(userAuthenticationProvider);
        auth.authenticationProvider(otpAuthenticationProvider);
        return auth.build();
    }

    @Bean
    public AuthStrategy authStrategy() {
        return new SessionAuthStrategy(userDetailsService);
//        return new JwtAuthStrategy();
    }
}
