package com.cbg.exam.service;

import com.cbg.exam.domain.dto.articleDto.ArticleWriteDto;
import com.cbg.exam.domain.entity.Article;
import com.cbg.exam.domain.entity.Board;
import com.cbg.exam.domain.entity.Member;
import com.cbg.exam.repository.ArticleRepository;
import com.cbg.exam.repository.MemberRepository;
import com.cbg.exam.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberService memberService;
    private final BoardService boardService;

    @Transactional
    public void save(ArticleWriteDto articleWriteDto, CustomUserDetails user, Long boardId){

        Member member = memberService.findByLoginId(user.getUsername());

        Board board = boardService.findById(boardId);

        articleRepository.save(articleWriteDto.toEntity(member, board));
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
