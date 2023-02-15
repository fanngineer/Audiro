package com.a402.audiro.exception;

public class NickNameExistException extends RuntimeException{

    public NickNameExistException(){
        super("nick-name exist");
    }
}
