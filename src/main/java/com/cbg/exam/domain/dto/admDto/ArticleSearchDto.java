package com.cbg.exam.domain.dto.admDto;

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

    // 게시판만 선택했을 경우
//    public ArticleSearchDto(String boardName){
//        this.boardName = boardName;
//        this.searchType = "";
//        this.searchKey = "";
//    }

    // 게시판 선택없이 검색 했을 경우
//    public ArticleSearchDto( String searchType, String searchKey ){
//        this.boardName = "";
//        this.searchType = searchType;
//        this.searchKey = searchKey;
//    }
}
