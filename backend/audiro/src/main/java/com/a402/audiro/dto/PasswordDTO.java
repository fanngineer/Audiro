package com.a402.audiro.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PasswordDTO {

    @NotBlank(message = "비밀번호 입력은 필수입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9ㄱ-ㅎ가-힣ㅏ-ㅣ]*$", message = "암호는 문자와 숫자로만 구성할 수 있습니다.")
    @Size(min = 3, max = 15, message = "비밀번호는 3자 이상, 15자 이하여야 합니다.")
    private String passwd;

}
