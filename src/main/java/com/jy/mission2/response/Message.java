package com.jy.mission2.response;

import lombok.Getter;

@Getter
public enum Message implements ResponseMessage{
    SUCCESS("SUCCESS");

    final String message;

    Message(String message){
        this.message = message;
    }

}
