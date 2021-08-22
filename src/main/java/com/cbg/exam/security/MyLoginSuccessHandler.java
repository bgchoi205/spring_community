package com.cbg.exam.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//public class MyLoginSuccessHandler implements AuthenticationSuccessHandler {
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication) throws IOException, ServletException {
//        HttpSession httpSession = request.getSession();
//
//        httpSession.setAttribute("welcome", authentication.getName() + "님 환영합니다.");
//
//
//        response.sendRedirect("/");  // 모든 로직이 끝난 후 사용자가 이동하게될 페이지
//
//    }
//}
