package com.cbg.exam.controller;

import com.cbg.exam.service.ArticleService;
import com.cbg.exam.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

        return "/adm/home";
    }

    @GetMapping("/articles")
    public String showAdmArticles(){

        return "/adm/articleManage";
    }

}
