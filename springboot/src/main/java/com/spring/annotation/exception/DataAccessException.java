package com.spring.annotation.exception;

public class DataAccessException extends RuntimeException{

    private String exMsg;

    public DataAccessException(String exMsg){
        this.exMsg = exMsg;
    }
}
