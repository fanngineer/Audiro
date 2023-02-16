package com.a402.audiro.exception;

public class PostcardNotExistException extends RuntimeException{
    public PostcardNotExistException(){
        super("존재하지 않는 엽서입니다.");
    }
}
