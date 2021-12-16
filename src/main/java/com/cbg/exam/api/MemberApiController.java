package com.cbg.exam.api;

import com.cbg.exam.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberApiController {
    private final MemberService memberService;

    @DeleteMapping("/member/{memberId}")
    public boolean delMember(@PathVariable(name="memberId") Long memberId){
        return memberService.apiDeleteMember(memberId);
    }
}
