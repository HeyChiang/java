package com.spring.annotation.exception;

public class PasswordException extends RuntimeException{

    private String exMsg;

    public PasswordException(String exMsg){
        this.exMsg = exMsg;
    }

}
