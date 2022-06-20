package com.jy.mission2.controller;

import com.jy.mission2.dto.request.BoardDto;
import com.jy.mission2.dto.response.BoardResponseDto;
import com.jy.mission2.dto.validation.ValidationGroup;
import com.jy.mission2.security.UserDetailsImpl;
import com.jy.mission2.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BoardController {

    private final BoardService boardService;

    @Autowired
    BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @GetMapping("/api/boards")
    public List<BoardResponseDto> getBoards(){
        return boardService.getBoards();
    }

    @PostMapping("/api/boards")
    public ResponseEntity<String> addBoard(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody BoardDto requestDto){
        return boardService.addBoard(userDetails, requestDto);
    }

    @DeleteMapping("/api/boards/{boardId}")
    public ResponseEntity<String> deleteBoard(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long boardId) {
        return boardService.deleteBoard(userDetails, boardId);
    }

    @PutMapping("/api/boards/{boardId}")
    public ResponseEntity<String> updateBoard(
            @Validated(ValidationGroup.Update.class)
            @RequestBody BoardDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long boardId) {

        return boardService.updateBoard(requestDto, userDetails, boardId);
        }
    }

