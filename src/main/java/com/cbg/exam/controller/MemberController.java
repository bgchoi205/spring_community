package com.cbg.exam.controller;

import com.cbg.exam.domain.dto.memberDto.MemberJoinDto;
import com.cbg.exam.domain.entity.Member;
import com.cbg.exam.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String memberLogin(){
        return "/usr/member/login";
    }

    @GetMapping("/join")
    public String memberJoin(){
        return "/usr/member/join";
    }


    @PostMapping("/join")
    public String memberDoJoin(MemberJoinDto memberJoinDto){

        memberService.save(memberJoinDto.toEntity());
        return "redirect:/";
    }

    @GetMapping("/list")
    public String memberList(Model model){
//        List members = memberService.findAll();
//        model.addAttribute("members", members);

        List<String> lists = new ArrayList<>();

        lists.add("<h1 class='test'>1번</h1>");
        lists.add("<h1 class='test'>2번</h1>");
        lists.add("<h1 class='test'>3번</h1>");
        lists.add("<h1 id='test'>4번</h1>");
        model.addAttribute("lists", lists);


        return "/usr/member/list";
    }

    @GetMapping("/mypage")
    public String memberMypage( Model model){

        String hi = "<h1>hihi</h1>";
        model.addAttribute("hi", hi);
        return "/usr/member/mypage";
    }

}
