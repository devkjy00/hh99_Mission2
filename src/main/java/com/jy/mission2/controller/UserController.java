package com.jy.mission2.controller;

import com.jy.mission2.dto.UserDto;
import com.jy.mission2.service.ResponseStatus;
import com.jy.mission2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


    @PostMapping("/api/signup")
    public ResponseStatus signup(@Valid @RequestBody UserDto requestDto){
        requestDto.checkPassword();
        return userService.signup(requestDto);
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

}
