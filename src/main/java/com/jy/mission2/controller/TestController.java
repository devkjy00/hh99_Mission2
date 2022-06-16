package com.jy.mission2.controller;

import com.jy.mission2.repository.BoardRepository;
import com.jy.mission2.repository.UserRepository;
import com.jy.mission2.security.UserDetailsImpl;
import com.jy.mission2.service.BoardService;
import com.jy.mission2.service.LikeService;
import com.jy.mission2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardService boardService;
    private final UserService userService;
    private final LikeService likeService;

    @Autowired
    public TestController(
            BoardRepository boardRepository,
            UserRepository userRepository,
            BoardService boardService,
            UserService userService,
            LikeService likeService) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.boardService = boardService;
        this.userService = userService;
        this.likeService = likeService;
    }


    @GetMapping("/test")
    public void getTest(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        likeService.toggleLike(userDetails, 2L);
    }
}
