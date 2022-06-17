package com.jy.mission2.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public enum SuccessMessage implements ResponseMessage{
    SUCCESS(HttpStatus.OK, "SuCcEsS");

    private final String message;
    private final HttpStatus httpStatus;

    SuccessMessage(HttpStatus httpStatus, String message){
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public ResponseEntity<String> getResponseEntity() {
        return new ResponseEntity<>(message, httpStatus);
    }
}
