package com.a402.audirochat.exception;

public class ChannelNotExistException extends RuntimeException{
    public ChannelNotExistException(){
        super("존재하지 않는 채널입니다.");
    }
}
