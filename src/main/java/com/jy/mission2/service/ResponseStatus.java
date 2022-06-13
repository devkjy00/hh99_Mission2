package com.jy.mission2.service;

public enum ResponseStatus {
    SUCCESS("Success"), FAIL("Fail");

    private String status;

    ResponseStatus(String status){
        this.status = status;
    }
}
