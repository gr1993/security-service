package com.example.normal.security.strategy;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

public class JwtAuthStrategy implements AuthStrategy {

    @Value("${jwt.signing.key}")
    private String signingKey;

    @Override
    public void ProcessAuthentication(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication) {

        SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));

        // 토큰 생성
        Date expirationDate = Date.from(Instant.now().plusSeconds(30 * 60));
        String jwt = Jwts.builder()
                .setClaims(Map.of("username", authentication.getName()))
                .setExpiration(expirationDate)
                .signWith(key)
                .compact();

        // 쿠키에 JWT 토큰 전달
        Cookie cookie = new Cookie("jwt", jwt);
        cookie.setMaxAge(30 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
