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


}
