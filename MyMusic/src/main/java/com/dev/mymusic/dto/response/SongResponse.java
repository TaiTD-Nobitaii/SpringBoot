package com.dev.mymusic.dto.response;

import com.dev.mymusic.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SongResponse {
    private UUID id;
    private String title;
    private String singer;
    private String genreName;
    private String lyrics;
    private String image;
    private String path;
    private Integer duration;
    private Integer quantity;
    private Boolean status;
}
