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

    @Transactional
    public String deleteBoard(
            UserDetailsImpl userDetails, Long boardId){

        Board board = findByUserIdAndId(userDetails, boardId);
        boardRepository.deleteById(board.getId());

        return Message.SUCCESS.getMessage();
    }

    @Transactional
    public String updateBoard(
            BoardDto requestDto,
            UserDetailsImpl userDetails, Long boardId) {

       Board board = findByUserIdAndId(userDetails, boardId);
       board.update(requestDto);

       return Message.SUCCESS.getMessage();
    }


    private Board findByUserIdAndId(
            UserDetailsImpl userDetails, Long boardId) {

        return boardRepository.findByUserIdAndId(userDetails.getId(), boardId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물 입니다"));
    }
}

