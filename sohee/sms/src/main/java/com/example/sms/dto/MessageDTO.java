package com.example.sms.dto;

import lombok.Getter;

@Getter
public class MessageDTO {

    private String nickname;
    private String phoneNumber;
    private String passwd;
    private String spot;
    private String message;

    public void makeSentence(){
        this.message = String.format(
                "\nAudi:ro\n\n"
                        + "%s 님께서 편지를 남기셨습니다.\n"
                        + "Audi:ro [%s]에 들러 확인해주세요\n\n"
                        + "[%s]\n\n"
                        + "위의 문구를 입력하시면 편지를 받아보실 수 있습니다."
        , this.nickname, this.spot, this.passwd);
    }
}
