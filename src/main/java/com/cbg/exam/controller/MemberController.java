package com.cbg.exam.controller;

import com.cbg.exam.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    // 로그인 화면
    @GetMapping("/login")
    public String memberLogin(){
        return "/adm/member/login";
    }

}
