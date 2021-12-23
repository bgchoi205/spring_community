package com.cbg.exam.api;

import com.cbg.exam.domain.entity.Article;
import com.cbg.exam.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ArticleApiController {

    private final ArticleService articleService;

    // 게시물 삭제(1개)
    @DeleteMapping("/article/{articleId}")
    public boolean deleteArticle(@PathVariable(name="articleId") Long articleId){
        return articleService.apiDelArticle(articleId);
    }

    // 게시물 삭제(여러개 선택삭제)
    @DeleteMapping("/articles/{articleIds}")
    public boolean deleteCheckedArticles(@PathVariable(name="articleIds") List<Long> articleIds){
        return articleService.apiDelCheckedArticles(articleIds);
    }

}
