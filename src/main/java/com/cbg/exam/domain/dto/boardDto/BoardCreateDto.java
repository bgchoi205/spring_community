package com.cbg.exam.domain.dto.boardDto;

import com.cbg.exam.domain.entity.Board;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardCreateDto {
    private String name;

    @Builder
    public BoardCreateDto(String name){
        this.name = name;
    }

    public Board toEntity(){
        return Board.builder()
                .name(name)
                .build();
    }
}
