package com.a402.audiro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongGiftListDTO {

    private long id;
    private String songTitle;
    private String singer;
    private String songUrl;
    private int songLiked;
    private int giftCnt;
    private List<String> giftList;

}
