package com.cbg.exam.controller;

import com.cbg.exam.domain.entity.Article;
import com.cbg.exam.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/usr/article/write")
    public String articleWrite(){
        return "/usr/article/write";
    }


    @PostMapping("/usr/article/write")
    public String articleDoWrite(
            @RequestParam("title") String title,
            @RequestParam("body") String body
    ){

        // 확인용 출력
        System.out.println("입력제목 : " + title);
        System.out.println("입력내용 : " + body);

        Article article =  Article.builder()
                .memberId(1L)
                .boardId(1L)
                .title(title)
                .body(body)
                .build();

        System.out.println("게시물제목 : " + article.getTitle());
        System.out.println("게시물내용 : " + article.getBody());

         articleService.save(article);

        System.out.println("새로 등록된 게시물 번호 : " + article.getId());

        return "redirect:/";
    }

    @GetMapping("/usr/article/list")
    public String memberList(Model model){
        List articles = articleService.findAll();
        model.addAttribute("articles", articles);
        return "/usr/article/list";
    }

}
