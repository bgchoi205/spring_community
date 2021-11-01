package com.cbg.exam.controller;

import com.cbg.exam.domain.entity.Article;
import com.cbg.exam.domain.entity.Board;
import com.cbg.exam.domain.entity.Member;
import com.cbg.exam.service.ArticleService;
import com.cbg.exam.service.BoardService;
import com.cbg.exam.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;
    private final ArticleService articleService;
    private final BoardService boardService;

    @GetMapping("/")
    public String home(){

        String logonMemberName = "";


        // 로그인한 회원 정보 가져오기(아이디)
        Object logonMember = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(logonMember instanceof UserDetails){
            logonMemberName = ((UserDetails) logonMember).getUsername();

        }else{
            logonMemberName = logonMember.toString();
        }

        return "home";
    }

    @GetMapping("/home/maketest")
    public String makeTest(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Member testMember =  Member.builder()
                .loginId("aa")
                .loginPw(passwordEncoder.encode("aa"))  // 가입시 비밀번호 암호화
                .name("aa")
                .nickname("aa")
                .email("aa@gmail.com")
                .build();

        testMember.changeAuth(7);

        memberService.save(testMember);

        Board testBoard = Board.builder()
                .name("Free")
                .build();
        boardService.save(testBoard);

        for(int i = 1; i <= 10; i++){
            Article article =  Article.builder()
                    .member(testMember)
                    .board(testBoard)
                    .title("제목" + i)
                    .articleHtml("<h1>hi" + i + "</h1>")
                    .articleMD("# hi" + i)
                    .build();
            articleService.save(article);
        }

        return "redirect:/";
    }

    @GetMapping("/test/toastUiTest")
    public String showToastUi(){
        return "test/toastUiTest";
    }
}
