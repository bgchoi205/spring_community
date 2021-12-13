package com.cbg.exam.api;


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

    @PostMapping("/board/{boardName}")
    public boolean addBoard(@PathVariable(name="boardName") String boardName, @AuthenticationPrincipal CustomUserDetails user){
        return boardService.checkBoardAndSave(boardName);
    }

    @PutMapping("/board/{boardName}")
    public boolean modifyBoardName(@PathVariable(name="boardName") String boardName){
        return boardService.modifyBoardName(boardName);
    }

    @DeleteMapping("/board/{boardName}")
    public boolean deleteBoard(@PathVariable(name="boardName") String boardName){
        return boardService.deleteBoard(boardName);
    }

}
