package com.cbg.exam.controller;

import com.cbg.exam.domain.dto.articleDto.ArticleSearchDto;
import com.cbg.exam.domain.dto.articleDto.ArticleModifyDto;
import com.cbg.exam.domain.dto.articleDto.ArticleWriteDto;
import com.cbg.exam.domain.dto.memberDto.MemberJoinDto;
import com.cbg.exam.domain.entity.Article;
import com.cbg.exam.domain.entity.Board;
import com.cbg.exam.domain.entity.Member;
import com.cbg.exam.security.CustomUserDetails;
import com.cbg.exam.service.ArticleService;
import com.cbg.exam.service.BoardService;
import com.cbg.exam.service.MemberService;
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
@RequestMapping("/adm")
public class AdmController {
    private final ArticleService articleService;
    private final BoardService boardService;
    private final MemberService memberService;

    // 계정 생성 화면
    @GetMapping("/join")
    public String memberJoin(){
        return "/adm/member/join";
    }

    // 계정 생성 처리
    @PostMapping("/join")
    public String memberDoJoin(MemberJoinDto memberJoinDto){

        memberService.save(memberJoinDto.toEntity());
        return "redirect:/";
    }

    // 게시물 작성 화면
    @GetMapping("/write")
    public String articleWrite(Model model){
        List<Board> boardList = boardService.findAll();

        model.addAttribute("boardList", boardList);
        return "/adm/article/write";
    }

    // 게시물 작성 처리
    @PostMapping("/write")
    public String articleDoWrite(ArticleWriteDto articleWriteDto, @AuthenticationPrincipal CustomUserDetails user){

        articleService.save(articleWriteDto, user);

        return "redirect:/";
    }

    // 게시물 수정 화면
    @GetMapping("/modify/{id}")
    public String showModifyArticle(@PathVariable("id") Long articleId, Model model){

        Article article = articleService.findById(articleId);
        List<Board> boardList = boardService.findAll();

        model.addAttribute("article", article);
        model.addAttribute("boardList", boardList);

        return "adm/article/modify";
    }

    // 게시물 수정 처리
    @PostMapping("/modify/{id}")
    public String ModifyArticle(@PathVariable("id") Long articleId, ArticleModifyDto articleModifyDto){
        Article article = articleService.findById(articleId);

        articleService.modifyArticle(article, articleModifyDto);

        return "redirect:/";
    }

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


        return "adm/article/articleManage";
    }

    // 관리자 페이지 - 게시판 관리
    @GetMapping("/boards")
    public String showAmdBoards(Model model){

        List<Board> boardList = boardService.findAll();

        model.addAttribute("boardList", boardList);

        return "adm/board/boardManage";
    }

    // 관리자 페이지 - 회원관리(매니저 관리)
    @GetMapping("/members")
    public String showAmdMembers(Model model){

        List<Member> memberList = memberService.findAll();

        model.addAttribute("memberList", memberList);

        return "adm/member/memberManage";
    }

}
