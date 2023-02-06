package com.a402.audiro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MusicmateDTO {
    private long id;
    private long userId;
    private long mateId;
    private boolean isMate;
    private boolean isBlock;
    private String mateNickname;
}
