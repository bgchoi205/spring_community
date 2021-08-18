package com.cbg.exam.service;

import com.cbg.exam.domain.Article;
import com.cbg.exam.domain.Member;
import com.cbg.exam.repository.ArticleRepository;
import com.cbg.exam.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public Long save(Article article){
        return articleRepository.save(article);
    }

    @Transactional
    public Long delete(Article article){
        return articleRepository.delete(article);
    }

    @Transactional
    public Long modify(Article article){
        return articleRepository.modify(article);
    }

    @Transactional
    public Article findById(int id){
        return articleRepository.findById(id);
    }

    @Transactional
    public List findAll() {
        return articleRepository.findAll();
    }

//    public List findAll() {
//        return memberRepository.findAll();
//    }
}
