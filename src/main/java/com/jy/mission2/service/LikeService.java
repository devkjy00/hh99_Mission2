package com.jy.mission2.service;

import com.jy.mission2.model.Board;
import com.jy.mission2.model.Like;
import com.jy.mission2.model.User;
import com.jy.mission2.repository.LikeRepository;
import com.jy.mission2.response.SuccessMessage;
import com.jy.mission2.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeService {

    private final int PLUS = 1;
    private final int MINUS = -1;

    private final LikeRepository likeRepository;
    private final UserService userService;
    private final BoardService boardService;

    @Autowired
    public LikeService(LikeRepository likeRepository, UserService userService, BoardService boardService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.boardService = boardService;
    }

    @Transactional
    public ResponseEntity<String> toggleLike(
            UserDetailsImpl userDetails, Long boardId){

        Like like = likeRepository.findByUserIdAndBoardId(userDetails.getId(), boardId)
                .orElseGet(() -> createLike(userDetails, boardId));

        Board board = like.getBoard();

        if(like.toggle()){
            board.updateLikeQty(PLUS);
        }else{
            board.updateLikeQty(MINUS);}


        return SuccessMessage.SUCCESS.getResponseEntity();
    }


    public Like createLike(
            UserDetailsImpl userDetails, Long boardId){

        User user = userService.getUser(userDetails);
        Board board = boardService.getBoardById(boardId);

        return likeRepository.save(new Like(user, board));
    }
}