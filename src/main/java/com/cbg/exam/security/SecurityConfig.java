package com.cbg.exam.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf().disable()  // csrf 토큰검사 비활성화
                .formLogin()  // form을 통해 로그인 활성
                .loginPage("/member/login");  // 로그인 페이지 접근할 때 띄워줄 페이지, 지정하지 않으면 Spring Security에서 제공하는 기본 폼이 나온다. loginPage(지정주소)의 지정주소가 controller에서 @GetMapping으로 받는 주소가일치해야 한다.




    }
}
