package com.example.oauth2.config;

import com.example.oauth2.service.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)  // csrf 비활성화 -> cookie를 사용하지 않으면 꺼도 된다. (cookie를 사용할 경우 httpOnly(XSS 방어), sameSite(CSRF 방어)로 방어해야 한다.)
                .cors(AbstractHttpConfigurer::disable) // cors 비활성화 -> 프론트와 연결 시 따로 설정 필요
                .httpBasic(AbstractHttpConfigurer::disable) // 기본 인증 로그인 비활성화
                .formLogin(AbstractHttpConfigurer::disable) // 기본 login form 비활성화
                .logout(logout ->
                    logout.logoutUrl("/logout")
                            .logoutSuccessUrl("/login")
                            .invalidateHttpSession(true)
                            .deleteCookies("JSESSIONID")
                )
                .oauth2Login(oauth ->
                    // baseUri 설정 제거 → 기본 /oauth2/authorization/{registrationId} 사용
                    // oauth.authorizationEndpoint(end -> end.baseUri("/kakao/login")) // OAuth2 인증 요청을 시작하는 url 설정
                    oauth.loginPage("/login") // Spring Security의 로그인 페이지가 아닌 커스텀 로그인 페이지 경로 지정
                            .defaultSuccessUrl("/") // 사용자가 지정한 페이지가 없고 로그인 성공 시 이동할 URL
                            .userInfoEndpoint(userInfo ->
                                    userInfo.userService(customOAuth2UserService) // OAuth 2 사용자정보 처리 서비스 등록
                            )
                )
                .authorizeHttpRequests(authorizeRequests -> {
                    authorizeRequests
                            .requestMatchers(
                                    "/login",
                                    "/.well-known/**", // 브라우저의 자동요청 문제로 경로 허용
                                    "/error"
                            ).permitAll()
                            .anyRequest().authenticated();
                });

        return http.build();
    }

}
