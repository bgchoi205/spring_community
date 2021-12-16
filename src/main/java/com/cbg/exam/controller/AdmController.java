package com.cbg.exam.controller;

import com.cbg.exam.domain.dto.admDto.ArticleSearchDto;
import com.cbg.exam.domain.entity.Article;
import com.cbg.exam.domain.entity.Board;
import com.cbg.exam.domain.entity.Member;
import com.cbg.exam.service.ArticleService;
import com.cbg.exam.service.BoardService;
import com.cbg.exam.service.MemberService;
import com.mysql.cj.xdevapi.Collection;
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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/adm")
public class AdmController {
    private final ArticleService articleService;
    private final BoardService boardService;
    private final MemberService memberService;

    // 관리자 페이지 - 홈
    @GetMapping("")
    public String showAdmPage(Model model){
        Long articleCounts = articleService.count();
        Long boardCounts = boardService.count();

        model.addAttribute("articleCounts", articleCounts);
        model.addAttribute("boardCounts", boardCounts);

        return "adm/home";
    }

    // 관리자 페이지 - 게시물 관리
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

    // 관리자 페이지 - 게시판 관리
    @GetMapping("/boards")
    public String showAmdBoards(Model model){

        List<Board> boardList = boardService.findAll();

        model.addAttribute("boardList", boardList);

        return "adm/boardManage";
    }

    // 관리자 페이지 - 회원관리(매니저 관리)
    @GetMapping("/members")
    public String showAmdMembers(Model model){

        List<Member> memberList = memberService.findAll();

        model.addAttribute("memberList", memberList);

        return "adm/memberManage";
    }

}
