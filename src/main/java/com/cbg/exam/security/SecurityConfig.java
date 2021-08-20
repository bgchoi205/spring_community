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
                .authorizeRequests()

                    .antMatchers("/member/login")
                    .permitAll()  // antMatchers로 지정한 페이지에는 모든 사용자가 접근 가능.
                    .anyRequest()  //  antMatchers로 지정한 페이지 이외의 다른모든 페이지(antMatchers로 지정하고 permitAll로 접근 허용을 지정 한 뒤에 써주기)
                    .authenticated() // 인증이 된 사용자만 접근할 수 있도록 제한
                .and()
                .formLogin()  // form을 통해 로그인 활성
                    .loginPage("/member/login")  // 로그인 페이지 접근할 때 띄워줄 페이지, 지정하지 않으면 Spring Security에서 제공하는 기본 폼이 나온다. loginPage(지정주소)의 지정주소가 controller에서 @GetMapping으로 받는 주소가일치해야 한다.
                    .loginProcessingUrl("/doLogin")  // 로그인 처리 URL 설정
                .usernameParameter("loginId")  // from에서 보내는 loginId를 받을 파라미터 key값
                .passwordParameter("loginPw")  // loginPw를 받을 파라미터 key값, 둘다 input의 name과 일치하도록.
                .successHandler(new MyLoginSuccessHandler())
                .and()
                .logout()  // 로그아웃 관련 설정 진행을 돕는 LogoutConfigurer<> 클래스를 반환.
                    .logoutUrl("/doLogout")  // logout 처리URL
                    .logoutSuccessUrl("/member/login");  // 로그아웃 후 이동할 페이지


    }
}
