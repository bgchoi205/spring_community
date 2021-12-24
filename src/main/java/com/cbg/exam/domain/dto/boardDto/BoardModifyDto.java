package com.cbg.exam.domain.dto.boardDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardModifyDto {
    private String prevBoardName;
    private String newBoardName;
}
