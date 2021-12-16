package com.cbg.exam.controller;

import com.cbg.exam.domain.dto.articleDto.ArticleModifyDto;
import com.cbg.exam.domain.dto.articleDto.ArticleWriteDto;
import com.cbg.exam.domain.entity.Article;
import com.cbg.exam.domain.entity.Board;
import com.cbg.exam.security.CustomUserDetails;
import com.cbg.exam.service.ArticleService;
import com.cbg.exam.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final BoardService boardService;

    // 게시물 작성 화면
    @GetMapping("/write")
    public String articleWrite(Model model){
        List<Board> boardList = boardService.findAll();

        model.addAttribute("boardList", boardList);
        return "/usr/article/write";
    }

    // 게시물 작성 처리
    @PostMapping("/write")
    public String articleDoWrite(ArticleWriteDto articleWriteDto, @AuthenticationPrincipal CustomUserDetails user){

        articleService.save(articleWriteDto, user);

        return "redirect:/";
    }

    // 게시물 리스트
    @GetMapping("")
    public String showArticleList(Model model){

        List<Board> boardList = boardService.findAll();
        List<Article> articleList = articleService.findAll();

        model.addAttribute("boardList", boardList);
        model.addAttribute("articleList", articleList);

        return "/usr/article/list";
    }

    // 게시물 상세보기 뷰
    @GetMapping("/{id}")
    public String showView(@PathVariable("id") Long articleId, Model model){
        Article article = articleService.findById(articleId);
        List<Board> boardList = boardService.findAll();

        model.addAttribute("article", article);
        model.addAttribute("boardList", boardList);

        return "usr/article/view";
    }

    // 게시물 수정 화면
    @GetMapping("/modify/{id}")
    public String showModifyArticle(@PathVariable("id") Long articleId, Model model){

        Article article = articleService.findById(articleId);
        List<Board> boardList = boardService.findAll();

        model.addAttribute("article", article);
        model.addAttribute("boardList", boardList);

        return "usr/article/modify";
    }

    // 게시물 수정 처리
    @PostMapping("/modify/{id}")
    public String ModifyArticle(@PathVariable("id") Long articleId, ArticleModifyDto articleModifyDto){
        Article article = articleService.findById(articleId);

        articleService.modifyArticle(article, articleModifyDto);

        return "redirect:/";
    }

}
