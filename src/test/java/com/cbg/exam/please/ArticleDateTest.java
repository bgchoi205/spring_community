package com.cbg.exam.please;

import com.cbg.exam.domain.entity.Article;
import com.cbg.exam.domain.entity.Member;
import com.cbg.exam.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Transactional
@SpringBootTest
class ArticleBoardTest {

    @Autowired
    private ArticleService articleService;

    @Transactional
    @Test
    public void articleBoardTest(){

        List<Article> articles = articleService.findAll();

        String boardName = articles.get(0).getBoard().getName();

        assertThat(boardName).isEqualTo("Notice");

    }
}
