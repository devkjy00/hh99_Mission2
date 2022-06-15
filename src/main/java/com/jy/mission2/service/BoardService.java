package com.jy.mission2.service;

import com.jy.mission2.dto.BoardResponseDto;
import com.jy.mission2.model.Board;
import com.jy.mission2.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    public List<BoardResponseDto> getBoards(Long userId) {
        List<Board> boardList = boardRepository.findAllByUserId(userId);

        List<BoardResponseDto> responseDtoList = new ArrayList<>();
        for (Board board : boardList) {
            responseDtoList.add(new BoardResponseDto(board));
        }
        return responseDtoList;
    }





}
