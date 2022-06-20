package com.jy.mission2.controller;

import com.jy.mission2.dto.request.BoardDto;
import com.jy.mission2.model.Board;
import com.jy.mission2.repository.BoardRepository;
import com.jy.mission2.repository.UserRepository;
import com.jy.mission2.security.UserDetailsImpl;
import com.jy.mission2.service.AwsS3Service;
import com.jy.mission2.service.BoardService;
import com.jy.mission2.service.LikeService;
import com.jy.mission2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test")
public class TestController {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardService boardService;
    private final UserService userService;
    private final LikeService likeService;
    private final AwsS3Service awsS3Service;

    @Autowired
    public TestController(
            BoardRepository boardRepository,
            UserRepository userRepository,
            BoardService boardService,
            UserService userService,
            AwsS3Service awsS3Service,
            LikeService likeService) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.boardService = boardService;
        this.userService = userService;
        this.likeService = likeService;
        this.awsS3Service = awsS3Service;
    }

}
