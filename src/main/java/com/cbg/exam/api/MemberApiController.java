package com.cbg.exam.api;

import com.cbg.exam.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberApiController {
    private final MemberService memberService;

    // 계정 삭제
    @DeleteMapping("/member/{memberId}")
    public boolean delMember(@PathVariable(name="memberId") Long memberId){
        return memberService.apiDeleteMember(memberId);
    }

    // 로그인아이디 중복 확인
    @GetMapping("/member/checkId/{loginId}")
    public boolean checkLoginId(@PathVariable(name="loginId") String loginId){
        return memberService.apiCheckLoginId(loginId);
    }

    // 닉네임 중복 확인
    @GetMapping("/member/checkNick/{nickname}")
    public boolean checkNickname(@PathVariable(name="nickname") String nickname){
        return memberService.apiCheckNickname(nickname);
    }
}
