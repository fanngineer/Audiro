package com.a402.audiro.dto;

import com.a402.audiro.entity.GiftTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManitoDTO {
    private Long userId;
    private Long songId;
    private GiftTag giftTag;
    private String giftImg;
    private Long spotId;
    private Long beforeManitoId;
}
