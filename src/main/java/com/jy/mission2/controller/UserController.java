package com.jy.mission2.controller;

import com.jy.mission2.dto.UserDto;
import com.jy.mission2.service.ResponseStatus;
import com.jy.mission2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


    @PostMapping("/api/signup")
    public ResponseStatus signup(@RequestBody UserDto requestDto) {
        return userService.signup(requestDto);
    }

}
