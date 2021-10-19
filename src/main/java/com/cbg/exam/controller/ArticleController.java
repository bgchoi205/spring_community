package com.cbg.exam.controller;

import com.cbg.exam.domain.dto.articleDto.ArticleWriteDto;
import com.cbg.exam.domain.entity.Article;
import com.cbg.exam.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/write")
    public String articleWrite(){
        return "/usr/article/write";
    }


    @PostMapping("/write")
    public String articleDoWrite(ArticleWriteDto articleWriteDto){

        // 확인용 출력
        System.out.println("입력html : " + articleWriteDto.getArticleHtml());
        System.out.println("입력MD : " + articleWriteDto.getArticleMD());

         articleService.save(articleWriteDto.toEntity(1L, 1L));

        return "redirect:/";
    }

    @GetMapping("")
    public String memberList(Model model){
        List articles = articleService.findAll();
        model.addAttribute("articles", articles);
        return "/usr/article/list";
    }

    @GetMapping("/{id}")
    public String showView(@PathVariable("id") Long articleId, Model model){

        Article article = articleService.findById(articleId);
        model.addAttribute("article", article);

        return "usr/article/view";
    }

}
