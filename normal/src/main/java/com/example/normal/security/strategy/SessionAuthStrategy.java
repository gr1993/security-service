package com.example.normal.security.strategy;

import com.example.normal.security.UserAuthentication;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

public class SessionAuthStrategy implements AuthStrategy {

    final private UserDetailsService userDetailsService;

    public SessionAuthStrategy(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void ProcessAuthentication(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());

        UserAuthentication auth = new UserAuthentication(userDetails.getUsername(), null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        // 세션에 인증 정보를 저장
        HttpSession session = request.getSession();
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
    }
}
