package com.a402.audiro.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class NaverSmsRequestDTO {
    String type;
    String contentType;
    String countryCode;
    String from;
    String content;
    List<NaverSmsMessageDTO> messages;
}
