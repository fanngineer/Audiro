package com.a402.audiro.exception;

public class MusicmateNotExistException extends RuntimeException{
    public MusicmateNotExistException(){
        super("뮤직메이트 관계가 아닙니다.");
    }
}
