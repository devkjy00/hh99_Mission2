package com.jy.mission2.controller;

import com.jy.mission2.security.UserDetailsImpl;
import lombok.Getter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/test")
    public void getTest(@AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.getNickname());
        System.out.println(userDetails.getId());
    }
}
