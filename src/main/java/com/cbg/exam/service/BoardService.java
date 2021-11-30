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

    @Transactional
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

}
