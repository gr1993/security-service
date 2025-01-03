package com.example.normal.security;


import com.example.normal.dto.ResponseDto;
import com.example.normal.security.strategy.AuthStrategy;
import com.example.normal.service.UserService;
import com.example.normal.util.HttpUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private AuthStrategy authStrategy;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("code");

        ResponseDto responseDto = new ResponseDto();
        try {
            //로그인 인증
            if (code == null) {
                Authentication a = new UserAuthentication(username, password);
                manager.authenticate(a);

                //OTP 코드 발급
                userService.renewOtp(username);

                HttpSession session = request.getSession(true);
                session.setAttribute("username", username);
                responseDto.setSuccess(true);
            }
            //코드 인증
            else {
                HttpSession session = request.getSession(false);
                if (session == null
                    || session.getAttribute("username") == null) {
                    throw new IllegalAccessException();
                }

                username = (String)session.getAttribute("username");
                Authentication a = new OtpAuthentication(username, code);
                manager.authenticate(a);

                //세션 방식, JWT 방식 선택적으로 처리 가능
                authStrategy.ProcessAuthentication(request, response, a);

                responseDto.setSuccess(true);
            }
        } catch (BadCredentialsException ex) {
            responseDto.setSuccess(false);
        } catch (IllegalAccessException ex) {
            responseDto.setSuccess(false);
            responseDto.setMsg("잘못된 접근입니다.");
        }
        HttpUtil.setJsonResponse(response, responseDto);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/loginAction");
    }
}
