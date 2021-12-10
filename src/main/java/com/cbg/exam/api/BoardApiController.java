package com.cbg.exam.api;


import com.cbg.exam.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/board/{boardName}")
    public boolean addBoard(@PathVariable(name="boardName") String boardName){
        return boardService.checkBoardAndSave(boardName);
    }

}
