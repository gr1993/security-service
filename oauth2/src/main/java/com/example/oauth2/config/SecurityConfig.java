package com.example.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)  // csrf 비활성화 -> cookie를 사용하지 않으면 꺼도 된다. (cookie를 사용할 경우 httpOnly(XSS 방어), sameSite(CSRF 방어)로 방어해야 한다.)
                .cors(AbstractHttpConfigurer::disable) // cors 비활성화 -> 프론트와 연결 시 따로 설정 필요
                .httpBasic(AbstractHttpConfigurer::disable) // 기본 인증 로그인 비활성화
                .logout(AbstractHttpConfigurer::disable) // 기본 logout 비활성화
                .formLogin(AbstractHttpConfigurer::disable) // 기본 login form 비활성화
                .oauth2Login(oauth ->
                    //  baseUri 설정 제거 → 기본 /oauth2/authorization/{registrationId} 사용
                    oauth.authorizationEndpoint(end -> end.baseUri("/kakao/login")) // OAuth2 인증 요청을 시작하는 url 설정
                          .loginPage("/login") // Spring Security의 로그인 페이지가 아닌 커스텀 로그인 페이지 경로 지정
                )
                .authorizeHttpRequests(authorizeRequests -> {
                    authorizeRequests
                            .requestMatchers(
                                    "/login"
                            ).permitAll()
                            .anyRequest().authenticated();
                });

        return http.build();
    }

}
