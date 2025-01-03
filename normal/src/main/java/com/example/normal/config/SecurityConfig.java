package com.example.normal.config;

import com.example.normal.security.CustomAuthenticationFilter;
import com.example.normal.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomAuthenticationFilter customAuthenticationFilter;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .addFilterAt(customAuthenticationFilter, BasicAuthenticationFilter.class)
//            .addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class)
            .formLogin(form -> {
                form.loginPage("/login")
                    .usernameParameter("username")
                    .passwordParameter("password");
            })
            .logout(logout -> {
                logout.logoutUrl("/logout")
                      .deleteCookies("JSESSIONID", "jwt", "remember-me");
            })
            .authorizeHttpRequests(authorizeRequests -> {
                authorizeRequests
                    .requestMatchers("/admin/**").hasAnyRole("admin")
                    .requestMatchers("/user/**").hasAnyRole("user")
                    .requestMatchers("/css/**").permitAll()
                    .requestMatchers(
                        "/login",
                        "/register",
                        "/logout",
                        "/code",
                        "/error"
                    ).permitAll()
                    .anyRequest().authenticated();
            });

        return http.build();
    }
}
