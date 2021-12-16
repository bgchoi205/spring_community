package com.cbg.exam.security;

import com.cbg.exam.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private MemberService memberService;

    @Override
    public void configure(WebSecurity webSecurity) throws Exception{
        // static 디렉터리 하위 파일들은 인증없이 언제나 통과
        webSecurity.ignoring().mvcMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
//                .csrf().disable()  // csrf 토큰검사 비활성화
                .authorizeRequests()
                    .mvcMatchers(
                            "/"
                            ,"/home/maketest"
                            ,"/test/toastUiTest"
                            ,"/articles").permitAll()  // 누구나 접근 가능
                    .mvcMatchers(
                            "/members/login").anonymous() // 인증하기 전에만 접근 가능
                    .mvcMatchers(
                            "/adm/**"
                            ,"/api/**"
                            ,"/articles/write"
                            ,"/members/join").hasRole("ADMIN")  // ADMIN 권한을 가진 계정만 접근 가능
                    .mvcMatchers(
                            "/members/mypage").hasRole("MEMBER")  // MEMBER 권한을 가진 계정만 접근 가능
//                    .anyRequest()  //  antMatchers로 지정한 페이지 이외의 다른모든 페이지
//                    .authenticated() // 인증이 된 사용자만 접근할 수 있도록 제한
                .and()// 로그인 설정 시작
                    .formLogin()  // form을 통해 로그인 활성
                    .loginPage("/members/login")  // 로그인 페이지 접근할 때 띄워줄 페이지, 지정하지 않으면 Spring Security에서 제공하는 기본 폼이 나온다. loginPage(지정주소)의 지정주소가 controller에서 @GetMapping으로 받는 주소가일치해야 한다.
                    .loginProcessingUrl("/doLogin")  // 로그인 처리 URL 설정
                    .usernameParameter("loginId")  // from에서 보내는 loginId를 받을 파라미터 key값
                    .passwordParameter("loginPw")  // loginPw를 받을 파라미터 key값, 둘다 input의 name과 일치하도록.
                    .defaultSuccessUrl("/")
                .and() //로그아웃 설정 시작
                    .logout()  // 로그아웃 관련 설정 진행을 돕는 LogoutConfigurer<> 클래스를 반환.
                    .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout")) // 로그아웃 주소 지정(따로 getMapping 할 필요는 없다)
                    .logoutSuccessUrl("/") // 로그아웃 성공 후 이동페이지
                    .invalidateHttpSession(true); // 로그아웃 시 인증정보 지우기, 세션 무효화



    }

    @Bean
    public PasswordEncoder passwordEncoder(){  // 비밀번호 암호화 객체
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }
    // spring security의 인증은 AuthenticationManager를 통해 이루어진다.
    // AuthenticationManager를 생성하기 위해 AuthenticationManagerBuilder 사용.
    // 인증을 위해서 UserDetailsService를 통해 필요한 정보를 가져온다.
    // MemberService에서 UserDetailsService 인터페이스를 implements해서 loadUserByUsername() 구현.

}
