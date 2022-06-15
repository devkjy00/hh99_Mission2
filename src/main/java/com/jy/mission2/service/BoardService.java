package com.jy.mission2.service;

import com.jy.mission2.model.Board;
import com.jy.mission2.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    public List<Board> getBoards(){
        return boardRepository.findAll();
    }





}
