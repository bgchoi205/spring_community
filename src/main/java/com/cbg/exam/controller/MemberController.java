package com.cbg.exam.controller;

import com.cbg.exam.domain.Member;
import com.cbg.exam.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/join")
    public String memberJoin(){
        return "/members/join";
    }

    @PostMapping("/members/join")
    public String memberDoJoin(@RequestParam("name") String name){
        Member member =  new Member();
        member.setName(name);

        memberService.save(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String memberList(Model model){
        List members = memberService.findAll();
        model.addAttribute("members", members);
        return "/members/memberList";
    }
}
