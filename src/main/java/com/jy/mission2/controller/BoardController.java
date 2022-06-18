package com.jy.mission2.controller;

import com.jy.mission2.dto.request.BoardDto;
import com.jy.mission2.dto.response.BoardResponseDto;
import com.jy.mission2.security.UserDetailsImpl;
import com.jy.mission2.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @GetMapping()
    public List<BoardResponseDto> getBoards(){
        return boardService.getBoards();
    }

    @PostMapping()
    public ResponseEntity<String> addBoard(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid BoardDto requestDto,
            @RequestPart List<MultipartFile> multipartFileList){
        return boardService.addBoard(userDetails, requestDto);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> deleteBoard(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long boardId) {
        return boardService.deleteBoard(userDetails, boardId);
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<String> updateBoard(
            @RequestBody BoardDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long boardId) {

        return boardService.updateBoard(requestDto, userDetails, boardId);
        }
    }

