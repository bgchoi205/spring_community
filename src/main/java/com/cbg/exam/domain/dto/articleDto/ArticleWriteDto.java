package com.cbg.exam.domain.dto.articleDto;

import com.cbg.exam.domain.entity.Article;
import com.cbg.exam.domain.entity.Board;
import com.cbg.exam.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ArticleWriteDto {
    private String title;
    private String articleHtml;
    private String articleMD;

    @Builder
    public ArticleWriteDto(String title, String articleHtml, String articleMD){
        this.title = title;
        this.articleHtml = articleHtml;
        this.articleMD = articleMD;
    }

    public Article toEntity(Member member, Board board){
        return Article.builder()
                .member(member)
                .board(board)
                .title(title)
                .articleHtml(articleHtml)
                .articleMD(articleMD)
                .build();
    }
}
