package com.cbg.exam.api;

import com.cbg.exam.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ArticleApiController {

    private final ArticleService articleService;

    @DeleteMapping("/article/{articleId}")
    public boolean deleteArticle(@PathVariable(name="articleId") Long articleId){
        return articleService.apiDelArticle(articleId);
    }

}
