package com.cbg.exam.controller;

import com.cbg.exam.domain.dto.articleDto.ArticleModifyDto;
import com.cbg.exam.domain.dto.articleDto.ArticleSearchDto;
import com.cbg.exam.domain.dto.articleDto.ArticleWriteDto;
import com.cbg.exam.domain.entity.Article;
import com.cbg.exam.domain.entity.Board;
import com.cbg.exam.security.CustomUserDetails;
import com.cbg.exam.service.ArticleService;
import com.cbg.exam.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public String showArticleList(Model model, ArticleSearchDto articleSearchDto,
                                  @PageableDefault(size = 12, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){

        List<Board> boardList = boardService.findAll();
//        List<Article> articleList = articleService.findAll();

        Page<Article> articleList = articleService.getArticlePage(articleSearchDto, pageable);

        int currentPage = pageable.getPageNumber() + 1;

        model.addAttribute("boardList", boardList);
        model.addAttribute("articleList", articleList);
        model.addAttribute("articleSearchDto", articleSearchDto);
        model.addAttribute("currentPage", currentPage);

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
