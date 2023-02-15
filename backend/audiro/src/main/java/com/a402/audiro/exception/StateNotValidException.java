package com.a402.audiro.exception;

public class StateNotValidException extends RuntimeException{
    public StateNotValidException(){
        super("유효하지 않은 상태값입니다.");
    }
}
