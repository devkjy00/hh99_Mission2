package com.jy.mission2.service;

import com.jy.mission2.model.Board;
import com.jy.mission2.model.Like;
import com.jy.mission2.model.User;
import com.jy.mission2.repository.LikeRepository;
import com.jy.mission2.response.Message;
import com.jy.mission2.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LikeService {

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
    public String toggleLike(
            UserDetailsImpl userDetails, Long boardId){

        Like like = likeRepository.findByUserIdAndBoardId(userDetails.getId(), boardId)
                .orElseGet(() -> createLike(userDetails, boardId));

        like.toggle();


        return Message.SUCCESS.getMessage();
    }


    private Like createLike(
            UserDetailsImpl userDetails, Long boardId){

        User user = userService.getUser(userDetails);
        Board board = boardService.getBoardById(boardId);

        return likeRepository.save(new Like(user, board));
    }
}