package com.a402.audiro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongSearchDTO {

    private long song_id;
    private String song_title;
    private String singer;
}
