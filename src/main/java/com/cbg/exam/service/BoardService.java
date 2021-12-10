package com.cbg.exam.service;

import com.cbg.exam.domain.entity.Board;
import com.cbg.exam.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void save(Board board){
        boardRepository.save(board);
    }

    public List<Board> findAll(){
        return boardRepository.findAll();
    }

    @Transactional
    public Board findById(Long boardId){
        return boardRepository.findById(boardId).orElseThrow();
    }

    @Transactional
    public Board findByName(String name){
        return boardRepository.findByName(name).orElseThrow();
    }

    @Transactional
    public Long count(){
        return boardRepository.count();
    }

    @Transactional
    public boolean checkBoardAndSave(String boardName) {

        if(isBoardEmpty(boardName)){
            Board board = Board.builder()
                    .name(boardName)
                    .build();
            boardRepository.save(board);
            return true;
        }
        return false;
    }

    private boolean isBoardEmpty(String boardName) {
        return boardRepository.findByName(boardName).isEmpty();
    }

}
