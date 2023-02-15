package com.a402.audiro.exception;

public class GiftNotExistException extends RuntimeException{
    public GiftNotExistException(){
        super("존재하지 않는 Gift입니다.");
    }

}
