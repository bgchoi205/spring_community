package com.cbg.exam.service;

import com.cbg.exam.domain.Article;
import com.cbg.exam.domain.Member;
import com.cbg.exam.repository.ArticleRepository;
import com.cbg.exam.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    public void save(Article article){
        articleRepository.save(article);
    }

    public Article findById(int id){
        return articleRepository.findById(id);
    }

    public List findAll() {
        return articleRepository.findAll();
    }

//    public List findAll() {
//        return memberRepository.findAll();
//    }
}
