package com.cbg.exam.controller;

import com.cbg.exam.domain.Article;
import com.cbg.exam.domain.Member;
import com.cbg.exam.security.SecurityConfig;
import com.cbg.exam.service.ArticleService;
import com.cbg.exam.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final SecurityConfig securityConfig;  // 비밀번호 암호화를 위해 생성자주입 추가

    @GetMapping("/member/login")
    public String memberLogin(){
        return "/member/login";
    }

//    @GetMapping("/member/logout")
//    public String memberLogout(){
//        return "/member/logout";
//    }


//    @PostMapping("/member/login")
//    public String memberDoLogin(
//            @RequestParam("loginId") String loginId,
//            @RequestParam("loginPw") String loginPw
//    ){
//        Optional<Member> member = memberService.findMemberByLoginId(loginId);
//
//        System.out.println("입력 아이디 : " + loginId);
//        System.out.println("입력 비밀번호 : " + loginPw);
//
//        return "redirect:/";
//    }

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


        memberService.save(loginId, loginPw, name, nickname, email);
        return "redirect:/";
    }

    @GetMapping("/member/list")
    public String memberList(Model model){
        List members = memberService.findAll();
        model.addAttribute("members", members);
        return "/member/list";
    }

    @GetMapping("/member/mypage")
    public String memberMypage(Model model){
        Member member = memberService.findById(1L);
        model.addAttribute("member", member);
        return "/member/mypage";
    }



//    @GetMapping("/members")
//    public String memberList(Model model){
//        List members = memberService.findAll();
//        model.addAttribute("members", members);
//        return "/members/memberList";
//    }
}
