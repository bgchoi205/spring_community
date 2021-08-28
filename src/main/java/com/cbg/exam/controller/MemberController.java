package com.cbg.exam.controller;

import com.cbg.exam.domain.Article;
import com.cbg.exam.domain.Member;
import com.cbg.exam.security.SecurityConfig;
import com.cbg.exam.service.ArticleService;
import com.cbg.exam.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/login")
    public String memberLogin(){
        return "/member/login";
    }

    @GetMapping("/member/join")
    public String memberJoin(){
        return "/member/join";
    }


    @PostMapping("/member/join")
    public String memberDoJoin(
            @RequestParam("loginId") String loginId,
            @RequestParam("loginPw") String loginPw,
            @RequestParam("name") String name,
            @RequestParam("nickname") String nickname,
            @RequestParam("email") String email
    ){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Member member =  Member.builder()
                .loginId(loginId)
                .loginPw(passwordEncoder.encode(loginPw))  // 가입시 비밀번호 암호화
                .name(name)
                .nickname(nickname)
                .email(email)
                .build();

        memberService.save(member);
        return "redirect:/";
    }

    @GetMapping("/member/list")
    public String memberList(Model model){
//        List members = memberService.findAll();
//        model.addAttribute("members", members);

        List<String> lists = new ArrayList<>();

        lists.add("<h1 class='test'>1번</h1>");
        lists.add("<h1 class='test'>2번</h1>");
        lists.add("<h1 class='test'>3번</h1>");
        lists.add("<h1 id='test'>4번</h1>");
        model.addAttribute("lists", lists);


        return "/member/list";
    }

    @GetMapping("/member/mypage")
    public String memberMypage( Model model){

        String hi = "<h1>hihi</h1>";
        model.addAttribute("hi", hi);
        return "/member/mypage";
    }

}
