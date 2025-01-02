package com.example.normal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .formLogin(form -> {
                form.loginPage("/login")
                    .usernameParameter("username")
                    .passwordParameter("password");
            })
            .logout(logout -> {
                logout.logoutUrl("/logout")
                      .deleteCookies("JSESSIONID", "remember-me");
            })
            .authorizeHttpRequests(authorizeRequests -> {
                authorizeRequests
                    .requestMatchers("/admin/**").authenticated()
                    .requestMatchers("/user/**").authenticated()
                    .requestMatchers("/css/**").permitAll()
                    .requestMatchers("/login", "/register", "logout").permitAll()
                    .anyRequest().authenticated();
            });

        return http.build();
    }
}
