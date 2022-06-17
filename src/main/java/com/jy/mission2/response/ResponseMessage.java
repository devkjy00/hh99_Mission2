package com.jy.mission2.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ResponseMessage {

    public ResponseEntity<String> getResponseEntity();
}
