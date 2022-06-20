package com.jy.mission2.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public enum FailureMessage implements ResponseMessage{

    OVERLAPPED_EMAIL(HttpStatus.BAD_REQUEST, "중복된 email 입니다"),
    NOT_VALID_ID_PASSWORD(HttpStatus.BAD_REQUEST, "이메일과 비밀번호를 다시 확인해주세요"),
    NO_DATA_EXIST(HttpStatus.BAD_REQUEST, "데이터를 찾을 수 없습니다"),
    NOT_ATHOURIZED(HttpStatus.BAD_REQUEST, "로그인이 필요합니다");

    private final HttpStatus httpStatus;
    private final String message;

    FailureMessage(HttpStatus httpStatus, String message){
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public ResponseEntity<String> getResponseEntity() {
        return new ResponseEntity<>(message, httpStatus);
    }
}
