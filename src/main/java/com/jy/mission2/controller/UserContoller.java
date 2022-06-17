package com.jy.mission2.controller;

import com.jy.mission2.dto.request.UserDto;
import com.jy.mission2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserContoller {

    private final UserService userService;

    @Autowired
    public UserContoller(UserService userService){
        this.userService = userService;
    }


    @PostMapping("/api/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody UserDto requestDto){
        requestDto.checkPassword();
        return userService.signup(requestDto);
    }
}
