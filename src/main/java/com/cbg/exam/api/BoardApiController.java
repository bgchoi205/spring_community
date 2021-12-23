package com.cbg.exam.api;


import com.cbg.exam.domain.dto.boardDto.BoardModifyDto;
import com.cbg.exam.security.CustomUserDetails;
import com.cbg.exam.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardApiController {

    private final BoardService boardService;

    // 게시판 생성
    @PostMapping("/board/{boardName}")
    public boolean addBoard(@PathVariable(name="boardName") String boardName, @AuthenticationPrincipal CustomUserDetails user){
        return boardService.checkBoardAndSave(boardName);
    }

    // 게시판 이름 수정
    @PutMapping("/board")
    public boolean modifyBoardName(@RequestBody BoardModifyDto boardModifyDto){

        return boardService.modifyBoardName(boardModifyDto);
    }

    // 게시판 삭제
    @DeleteMapping("/board/{boardName}")
    public boolean deleteBoard(@PathVariable(name="boardName") String boardName){
        return boardService.deleteBoard(boardName);
    }

}
