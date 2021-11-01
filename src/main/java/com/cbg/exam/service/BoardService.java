package com.cbg.exam.service;

import com.cbg.exam.domain.entity.Board;
import com.cbg.exam.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void save(Board board){
        boardRepository.save(board);
    }

    public List<Board> findAll(){
        return boardRepository.findAll();
    }

    public Board findById(Long boardId){
        return boardRepository.findById(boardId).orElseThrow();
    }

    public Board findByName(String name){
        return boardRepository.findByName(name).orElseThrow();
    }
}
