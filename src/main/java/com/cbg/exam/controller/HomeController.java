package com.cbg.exam.controller;

import com.cbg.exam.domain.Article;
import com.cbg.exam.domain.Member;
import com.cbg.exam.service.ArticleService;
import com.cbg.exam.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    private final ArticleService articleService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/home/maketest")
    public String makeTest(){
        Member member =  Member.builder()
                .loginId("aa")
                .loginPw("aa")
                .name("aa")
                .nickname("aa")
                .email("aa@gmail.com")
                .build();
        memberService.save(member);

        for(int i = 1; i <= 10; i++){
            Article article =  Article.builder()
                    .memberId(1L)
                    .boardId(1L)
                    .title("제목 : " + i)
                    .body("내용 : " + i)
                    .build();
            articleService.save(article);
        }

        return "redirect:/";
    }
}
