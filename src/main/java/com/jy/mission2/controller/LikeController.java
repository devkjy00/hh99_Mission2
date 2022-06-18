package com.jy.mission2.controller;

import com.jy.mission2.security.UserDetailsImpl;
import com.jy.mission2.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boards/{boardId}/likes")
public class LikeController {

    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping()
    public ResponseEntity<String> createLike(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long boardId){

        return likeService.toggleLike(userDetails, boardId);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteLike(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long boardId){

        return likeService.toggleLike(userDetails, boardId);
    }
}
