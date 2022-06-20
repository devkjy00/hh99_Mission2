package com.jy.mission2.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DataNotFoundException extends RuntimeException{

        public DataNotFoundException(String message){
                super(message);
        }
}
