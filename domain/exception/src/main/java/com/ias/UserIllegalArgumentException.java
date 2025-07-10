package com.ias;

public class UserIllegalArgumentException extends RuntimeException {
    public UserIllegalArgumentException(String message){
        super(message);
    }
}