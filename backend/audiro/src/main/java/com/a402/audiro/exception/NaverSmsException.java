package com.a402.audiro.exception;

public class NaverSmsException extends RuntimeException{
    public NaverSmsException(String msg){
        super("NaverSms에서 에러 발생 ! : " + msg);
    }
}
