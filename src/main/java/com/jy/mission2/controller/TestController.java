package com.jy.mission2.controller;

import com.jy.mission2.dto.BoardDto;
import com.jy.mission2.model.Board;
import com.jy.mission2.model.User;
import com.jy.mission2.repository.BoardRepository;
import com.jy.mission2.repository.UserRepository;
import com.jy.mission2.security.UserDetailsImpl;
import com.jy.mission2.service.BoardService;
import com.jy.mission2.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardService boardService;
    private final UserService userService;

    @Autowired
    public TestController(
            BoardRepository boardRepository,
            UserRepository userRepository,
            BoardService boardService,
            UserService userService) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.boardService = boardService;
        this.userService = userService;
    }


    @GetMapping("/test")
    public void getTest(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userRepository.findById(userDetails.getId())
                .orElseThrow(() -> new IllegalArgumentException("없음"));

        List<Board> boardList = user.getBoardList();



        Board board = boardRepository.findById(2L)
                .orElseThrow(() -> new IllegalArgumentException("없음"));

        BoardDto dto = new BoardDto(2, "url", "변경");

        board.update(dto);

        boardList.add(board);
        user.setBoardList(boardList);
        userRepository.save(user);


    }
}
