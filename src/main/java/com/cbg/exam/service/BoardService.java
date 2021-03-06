package com.cbg.exam.service;

import com.cbg.exam.domain.dto.boardDto.BoardModifyDto;
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

    public Board findById(Long boardId){
        return boardRepository.findById(boardId).orElseThrow();
    }

    public Board findByName(String name){
        return boardRepository.findByName(name).orElseThrow();
    }

    public Long count(){
        return boardRepository.count();
    }

    // 게시판 유무 체크
    private boolean isBoardEmpty(String boardName) {
        return boardRepository.findByName(boardName).isEmpty();
    }

    // 게사판 생성 - 생성하려는 게시판 이름 체크 후 없으면 생성
    @Transactional
    public boolean checkBoardAndSave(String boardName) {
        if( !isBoardEmpty(boardName) ){
            return false;
        }

        Board board = Board.builder()
                .name(boardName)
                .build();
        boardRepository.save(board);

        return true;
    }

    // 게시판 이름 수정
    @Transactional
    public boolean modifyBoardName(BoardModifyDto boardModifyDto) {
        if( isBoardEmpty(boardModifyDto.getPrevBoardName()) ){
            return false;
        }

        Board board = findByName(boardModifyDto.getPrevBoardName());
        board.changeName(boardModifyDto.getNewBoardName());
        return true;
    }

    // 게시판 삭제
    @Transactional
    public boolean deleteBoard(String boardName) {
        if( isBoardEmpty(boardName) ){
            return false;
        }

        Board board = findByName(boardName);
        boardRepository.delete(board);
        return true;
    }
}
