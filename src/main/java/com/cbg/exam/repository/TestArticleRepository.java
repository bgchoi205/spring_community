package com.cbg.exam.repository;

import com.cbg.exam.domain.Article;
import com.cbg.exam.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestArticleRepository extends JpaRepository<Article, String> {

}
