package com.jy.mission2.response;

import lombok.Getter;

@Getter
public enum ErrMessage implements ResponseMessage{

    OVERLAPPED_EMAIL("중복된 email 입니다");
    public final String message;

    ErrMessage(String message){
        this.message = message;
    }

}
