package com.cbg.exam.domain.dto.articleDto;

import com.cbg.exam.domain.entity.Article;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ArticleWriteDto {
    private String articleHtml;
    private String articleMD;

    @Builder
    public ArticleWriteDto(String articleHtml, String articleMD){
        this.articleHtml = articleHtml;
        this.articleMD = articleMD;
    }

    public Article toEntity(Long memberId, Long boardId){
        return Article.builder()
                .memberId(memberId)
                .boardId(boardId)
                .articleHtml(articleHtml)
                .articleMD(articleMD)
                .build();
    }
}
