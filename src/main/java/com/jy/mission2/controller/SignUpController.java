package com.jy.mission2.controller;

import com.jy.mission2.dto.UserDto;
import com.jy.mission2.exception.NameOverlappedPasswordException;
import com.jy.mission2.response.ResponseMessage;
import com.jy.mission2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.naming.NameAlreadyBoundException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SignUpController {

    private final UserService userService;

    @Autowired
    public SignUpController(UserService userService){
        this.userService = userService;
    }


    @PostMapping("/api/signup")
    public String signup(@Valid @RequestBody UserDto requestDto){
        requestDto.checkPassword();
        return userService.signup(requestDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(NameOverlappedPasswordException.class)
    public ResponseEntity<String> handleValidationExceptions(NameOverlappedPasswordException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
