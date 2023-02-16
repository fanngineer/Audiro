package com.a402.audiro.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostcardDTO {
    private long sendId;

    @NotBlank(message = "닉네임 입력은 필수입니다.")
    private String nickname;

    @NotBlank(message = "전화번호 입력은 필수입니다.")
    @Pattern(regexp = "^[0-9]*$", message = "숫자만 입력해주세요.")
    private String phoneNumber;

    @NotBlank(message = "비밀번호 입력은 필수입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9ㄱ-ㅎ가-힣ㅏ-ㅣ]*$", message = "암호는 문자와 숫자로만 구성할 수 있습니다.")
    @Size(min = 3, max = 15, message = "비밀번호는 3자 이상, 15자 이하여야 합니다.")
    private String passwd;

    @NotNull(message = "노래 입력은 필수입니다.")
    private long songId;
    @NotNull(message = "지점 입력은 필수입니다.")
    private long spotId;
    @NotNull(message = "지점 입력은 필수입니다.")
    private String spotName;

    @NotNull(message = "엽서 입력은 필수입니다.")
    private String postcardImg;
    private LocalDateTime regTime;
    private String message;

    public void init(){
        this.message = String.format(
                "\nAudi:ro\n\n"
                        + "%s 님께서 편지를 남기셨습니다.\n"
                        + "Audi:ro [%s]에 들러 확인해주세요\n\n"
                        + "[%s]\n\n"
                        + "위의 문구를 입력하시면 편지를 받아보실 수 있습니다."
                , this.nickname, this.spotName, this.passwd);
        this.regTime = LocalDateTime.now();
    }

}
