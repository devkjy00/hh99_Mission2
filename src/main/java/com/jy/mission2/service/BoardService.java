package com.jy.mission2.service;

import com.jy.mission2.dto.request.BoardDto;
import com.jy.mission2.dto.response.BoardResponseDto;
import com.jy.mission2.exception.DataNotFoundException;
import com.jy.mission2.model.Board;
import com.jy.mission2.model.User;
import com.jy.mission2.repository.BoardRepository;
import com.jy.mission2.response.FailureMessage;
import com.jy.mission2.response.SuccessMessage;
import com.jy.mission2.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserService userService;
    private final AwsS3Service awsS3Service;


    @Autowired
    public BoardService(BoardRepository boardRepository,
                        UserService userService,
                        AwsS3Service awsS3Service) {
        this.boardRepository = boardRepository;
        this.userService = userService;
        this.awsS3Service = awsS3Service;
    }


    @Transactional(readOnly = true)
    public List<BoardResponseDto> getBoards() {
        List<Board> boardList = boardRepository.findAll(Sort.by(Sort.Direction.DESC,"likeQty"));

        return BoardResponseDto.getDtoList(boardList);
    }


    @Transactional
    public ResponseEntity<String> addBoard(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            BoardDto boardDto) {

        User user = userService.getUser(userDetails);

        Board board = boardDto.getBoard(user, awsS3Service);
        boardRepository.save(board);

        return SuccessMessage.SUCCESS.getResponseEntity();

    }


    @Transactional
    public ResponseEntity<String> deleteBoard(
            UserDetailsImpl userDetails, Long boardId) {

        Board board = findByIdAndUserId(boardId, userDetails);

        awsS3Service.deleteFile(board.getImgUrl());
        boardRepository.deleteById(board.getId());

        return SuccessMessage.SUCCESS.getResponseEntity();
    }


    @Transactional
    public ResponseEntity<String> updateBoard(
            BoardDto requestDto,
            UserDetailsImpl userDetails, Long boardId) {

        Board board = findByIdAndUserId(boardId, userDetails);
        board.updateFields(requestDto, awsS3Service);

        return SuccessMessage.SUCCESS.getResponseEntity();
    }


    @Transactional(readOnly = true)
    public Board findByIdAndUserId(
            Long boardId, UserDetailsImpl userDetails) {

        return boardRepository.findByIdAndUserId(boardId, userDetails.getId())
                .orElseThrow(() -> new DataNotFoundException(FailureMessage.NO_DATA_EXIST.getMessage()));
    }


    @Transactional(readOnly = true)
    public Board getBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(FailureMessage.NO_DATA_EXIST.getMessage()));
    }
}

