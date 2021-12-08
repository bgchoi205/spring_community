package com.cbg.exam.controller;

import com.cbg.exam.domain.dto.admDto.ArticleSearchDto;
import com.cbg.exam.domain.entity.Article;
import com.cbg.exam.domain.entity.Board;
import com.cbg.exam.service.ArticleService;
import com.cbg.exam.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/adm")
public class AdmController {
    private final ArticleService articleService;
    private final BoardService boardService;

    @GetMapping("")
    public String showAdmPage(Model model){
        Long articleCounts = articleService.count();
        Long boardCounts = boardService.count();

        model.addAttribute("articleCounts", articleCounts);
        model.addAttribute("boardCounts", boardCounts);

        return "adm/home";
    }

    @GetMapping("/articles")
    public String showAdmArticles(Model model, ArticleSearchDto articleSearchDto,
                                  @PageableDefault(size = 8, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){

        List<Board> boardList = boardService.findAll();
        Page<Article> articleList = articleService.getArticlePage(articleSearchDto, pageable);


        int currentPage = pageable.getPageNumber() + 1;

        model.addAttribute("boardList", boardList);
        model.addAttribute("articleList", articleList);
        model.addAttribute("articleSearchDto", articleSearchDto);
        model.addAttribute("currentPage", currentPage);


        return "adm/articleManage";
    }

}
