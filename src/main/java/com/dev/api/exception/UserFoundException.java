package com.dev.api.exception;

public class UserFoundException extends RuntimeException{
    public UserFoundException(String message){
        super(message);
    }
}
