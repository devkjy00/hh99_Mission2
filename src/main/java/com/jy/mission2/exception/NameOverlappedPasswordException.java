package com.jy.mission2.exception;

import com.jy.mission2.response.ErrMessage;

public class NameOverlappedPasswordException extends RuntimeException{

        public NameOverlappedPasswordException(String message){
                super(message);
        }

}
