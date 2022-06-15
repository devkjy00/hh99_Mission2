package com.jy.mission2.controller;

import com.jy.mission2.dto.BoardDto;
import com.jy.mission2.dto.BoardResponseDto;
import com.jy.mission2.model.Board;
import com.jy.mission2.security.UserDetailsImpl;
import com.jy.mission2.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class BoardController {

    private final BoardService boardService;

    @Autowired
    BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @GetMapping("/api/boards")
    public List<BoardResponseDto> getBoards(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return boardService.getBoards(userDetails.getId());
    }

    @PostMapping("/api/boards")
    public String addBoard(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody BoardDto boardDto){
        return boardService.addBoard(userDetails, boardDto);
    }
}
