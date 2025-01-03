package com.example.normal.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface AuthStrategy {

    void ProcessAuthentication(HttpServletRequest request, Authentication authentication);
}
