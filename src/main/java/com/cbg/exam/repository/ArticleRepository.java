package com.cbg.exam.repository;

import com.cbg.exam.domain.entity.Article;
import com.cbg.exam.domain.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findByBoardAndTitleContaining(Board board, String searchKey, Pageable pageable);

    Page<Article> findByBoard(Board board, Pageable pageable);

    Page<Article> findByTitleContaining(String searchKey, Pageable pageable);

    List<Article> findFirst3ByOrderByIdDesc();
}


