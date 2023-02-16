package com.a402.audiro.exception;

public class MusicmateAlreadyExistException extends RuntimeException{
    public MusicmateAlreadyExistException(){
        super("이미 팔로우중입니다.");
    }
}
