package com.cbg.exam.service;

import com.cbg.exam.domain.entity.Article;
import com.cbg.exam.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public void save(Article article){
        articleRepository.save(article);
    }

    @Transactional
    public void delete(Article article){
        articleRepository.delete(article);
    }

//    @Transactional
//    public Long modify(Article article){
//        return articleRepository.modify(article);
//    }

    @Transactional
    public Article findById(Long id){
        Article findByIdArticle = articleRepository.findById(id).orElseThrow();

        return findByIdArticle;
    }

    @Transactional
    public List findAll() {
        return articleRepository.findAll();
    }

}
