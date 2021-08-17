package com.cbg.exam.controller;

import com.cbg.exam.domain.Article;
import com.cbg.exam.domain.Member;
import com.cbg.exam.service.ArticleService;
import com.cbg.exam.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/article/write")
    public String articleWrite(){
        return "/article/write";
    }


    @PostMapping("/article/write")
    public String articleDoWrite(
            @RequestParam("title") String title,
            @RequestParam("body") String body
    ){

        String dateTimeStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Article article =  Article.builder()
                .memberId(1L)
                .boardId(1L)
                .regDate(dateTimeStr)
                .updateDate(dateTimeStr)
                .title(title)
                .body(body)
                .build();

        articleService.save(article);
        return "redirect:/";
    }

    @GetMapping("/article/list")
    public String memberList(Model model){
        List articles = articleService.findAll();
        model.addAttribute("articles", articles);
        return "/article/list";
    }

//    @GetMapping("/members")
//    public String memberList(Model model){
//        List members = memberService.findAll();
//        model.addAttribute("members", members);
//        return "/members/memberList";
//    }
}
