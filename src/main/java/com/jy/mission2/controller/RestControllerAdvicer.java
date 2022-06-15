package com.jy.mission2.controller;

import com.jy.mission2.exception.NameOverlappedPasswordException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestControllerAdvicer {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex){
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors()
//                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
//        return ResponseEntity.badRequest().body(errors);
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(NameOverlappedPasswordException.class)
    public ResponseEntity<String> handleValidationExceptions(NameOverlappedPasswordException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
