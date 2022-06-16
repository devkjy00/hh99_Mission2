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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<Board> boardList = boardRepository.findAllByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재 하지 않습니다"));

        return BoardResponseDto.getDtoList(boardList);
    }

    public String addBoard(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            BoardDto boardDto){

        User user = userService.getUser(userDetails);

        Board board = boardDto.getBoard(user);
        boardRepository.save(board);

        return Message.SUCCESS.getMessage();

    }

    @Transactional
    public String deleteBoard(
            UserDetailsImpl userDetails, Long boardId){

        Board board = findByIdAndUserId(boardId, userDetails);
        boardRepository.deleteById(board.getId());

        return Message.SUCCESS.getMessage();
    }

    @Transactional
    public String updateBoard(
            BoardDto requestDto,
            UserDetailsImpl userDetails, Long boardId) {

       Board board = findByIdAndUserId(boardId, userDetails);
       board.update(requestDto);

       return Message.SUCCESS.getMessage();
    }


    private Board findByIdAndUserId(
            Long boardId, UserDetailsImpl userDetails){

        return boardRepository.findByIdAndUserId(boardId, userDetails.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물 입니다"));
    }

    public Board getBoardById(Long id){
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물"));
    }
}

