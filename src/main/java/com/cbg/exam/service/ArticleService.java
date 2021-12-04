package com.cbg.exam.service;

import com.cbg.exam.domain.dto.admDto.ArticleSearchDto;
import com.cbg.exam.domain.dto.articleDto.ArticleWriteDto;
import com.cbg.exam.domain.entity.Article;
import com.cbg.exam.domain.entity.Board;
import com.cbg.exam.domain.entity.Member;
import com.cbg.exam.repository.ArticleRepository;
import com.cbg.exam.repository.MemberRepository;
import com.cbg.exam.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberService memberService;
    private final BoardService boardService;

    @Transactional
    public void save(ArticleWriteDto articleWriteDto, CustomUserDetails user){

        Member member = memberService.findByLoginId(user.getUsername());

        Board board = boardService.findByName(articleWriteDto.getBoardName());

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

//    public List<Article> findAll() {
//        return articleRepository.findAllJoinFetch();
//    }

    public List<Article> findAll(){
        return articleRepository.findAll();
    }

    @Transactional
    public Long count(){
        return articleRepository.count();
    }

    public Page<Article> getArticlePage(ArticleSearchDto articleSearchDto, Pageable pageable) {

        if( articleSearchDto.getBoardName().isBlank() && articleSearchDto.getSearchKey().isBlank() ){
            return articleRepository.findAll(pageable);
        }

        if( !articleSearchDto.getBoardName().isBlank() && !articleSearchDto.getSearchKey().isBlank() ){
            Board board = boardService.findByName(articleSearchDto.getBoardName());
            return articleRepository.findByBoardAndTitleContaining(board, articleSearchDto.getSearchKey(), pageable);
        }

        if( !articleSearchDto.getBoardName().isBlank() && articleSearchDto.getSearchKey().isBlank() ){
            Board board = boardService.findByName(articleSearchDto.getBoardName());
            return articleRepository.findByBoard(board, pageable);
        }

        if ( articleSearchDto.getBoardName().isBlank() && !articleSearchDto.getSearchKey().isBlank() ) {
            return articleRepository.findByTitleContaining(articleSearchDto.getSearchKey(), pageable);
        }

        return articleRepository.findAll(pageable);
    }
}
