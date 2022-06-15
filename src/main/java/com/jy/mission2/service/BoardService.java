package com.jy.mission2.service;

import com.jy.mission2.dto.BoardDto;
import com.jy.mission2.dto.BoardResponseDto;
import com.jy.mission2.model.Board;
import com.jy.mission2.model.User;
import com.jy.mission2.repository.BoardRepository;
import com.jy.mission2.response.Message;
import com.jy.mission2.response.ResponseMessage;
import com.jy.mission2.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    private final UserService userService;

    @Autowired
    public BoardService(BoardRepository boardRepository, UserService userService){
        this.boardRepository = boardRepository;
        this.userService = userService;
    }

    public List<BoardResponseDto> getBoards(Long userId) {
        List<Board> boardList = boardRepository.findAllByUserId(userId);

        List<BoardResponseDto> responseDtoList = new ArrayList<>();
        for (Board board : boardList) {
            responseDtoList.add(new BoardResponseDto(board));
        }
        return responseDtoList;
    }

    public String addBoard(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            BoardDto boardDto){

        User user = userService.getUser(userDetails);

        Board board = new Board(boardDto, user);
        boardRepository.save(board);

        return Message.SUCCESS.getMessage();

    }




}
