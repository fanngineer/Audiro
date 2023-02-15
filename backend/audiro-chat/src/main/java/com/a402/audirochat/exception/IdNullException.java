package com.a402.audirochat.exception;

public class IdNullException extends RuntimeException{

    public IdNullException(){
        super ("User Id는 NULL일 수 없습니다.");
    }
}
