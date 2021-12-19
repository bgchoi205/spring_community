package com.cbg.exam.domain.dto.articleDto;

import lombok.Data;

@Data
public class ArticleSearchDto {

    private String boardName;

    private String searchType;

    private String searchKey;

    // null 값을 막기 위해 기본값 공백 지정
    public ArticleSearchDto(){
        this.boardName = "";
        this.searchType = "";
        this.searchKey = "";
    }

}
