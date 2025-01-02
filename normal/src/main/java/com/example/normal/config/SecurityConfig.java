package com.example.normal.config;

import com.example.normal.repository.UserRepository;
import com.example.normal.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private UserRepository userRepository;

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
                    .requestMatchers("/admin/**").hasAnyRole("admin")
                    .requestMatchers("/user/**").hasAnyRole("user")
                    .requestMatchers("/css/**").permitAll()
                    .requestMatchers("/login", "/register", "/logout").permitAll()
                    .anyRequest().authenticated();
            });

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
