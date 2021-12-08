package com.cbg.exam.domain.dto.articleDto;

import com.cbg.exam.domain.entity.Article;
import com.cbg.exam.domain.entity.Board;
import com.cbg.exam.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ArticleWriteDto {
    private String title;
    private String articleMD;
    private String boardName;

    @Builder
    public ArticleWriteDto(String title, String articleMD, String boardName){
        this.title = title;
        this.articleMD = articleMD;
        this.boardName = boardName;
    }

    public Article toEntity(Member member, Board board){
        return Article.builder()
                .member(member)
                .board(board)
                .title(title)
                .articleMD(articleMD)
                .build();
    }
}
