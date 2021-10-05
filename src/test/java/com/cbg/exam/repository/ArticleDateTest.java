package com.cbg.exam.repository;

import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ArticleDateTest {



//    @Test
//   public void localDateTest(){
//       LocalDateTime now = LocalDateTime.of(2020, 8, 12, 0, 0, 0);
//       testArticleRepository.save(Article.builder()
//               .memberId(1L)
//               .boardId(1L)
//               .title("titleTest")
//               .body("bodyTest")
//               .build());
//
//       // when
//       List<Article> articles = testArticleRepository.findAll();
//
//       //then
//       Article article = articles.get(0);
//
//       System.out.println(">>>>>>> createdDate=" + article.getCreatedDate() + ", modifiedDate=" + article.getModifiedDate());
//
//       assertThat(article.getCreatedDate()).isAfter(now);
//       assertThat(article.getModifiedDate()).isAfter(now);
//   }
}
