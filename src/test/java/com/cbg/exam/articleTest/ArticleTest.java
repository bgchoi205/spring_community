package com.cbg.exam.articleTest;

import com.cbg.exam.domain.dto.articleDto.ArticleModifyDto;
import com.cbg.exam.domain.entity.Article;
import com.cbg.exam.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Transactional
@SpringBootTest
class ArticleTest {

    @Autowired
    private ArticleService articleService;

    @Test
    public void articleModifyTest(){
        Article article = articleService.findById(14L);

        ArticleModifyDto articleModifyDto = ArticleModifyDto.builder()
                .title("modTest")
                .articleMD("# testTest")
                .boardName("Java")
                .build();

        articleService.modifyArticle(article, articleModifyDto);

        assertThat(article.getTitle()).isEqualTo("modTest");
    }

}
