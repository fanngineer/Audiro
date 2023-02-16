package com.a402.audiro.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class NaverSmsMessageDTO {

    String to;
    String content;
}
