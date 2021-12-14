package com.cbg.exam.domain.dto.articleDto;

import com.cbg.exam.domain.entity.Board;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ArticleModifyDto {
    private String title;
    private String articleMD;
    private String boardName;

    @Builder
    public ArticleModifyDto(String title, String articleMD, String boardName){
        this.title = title;
        this.articleMD = articleMD;
        this.boardName = boardName;
    }

}
