package com.example.normal.security.strategy;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

public interface AuthStrategy {

    void ProcessAuthentication(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication
    );
}
