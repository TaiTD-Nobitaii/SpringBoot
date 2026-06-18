package com.dev.mymusic.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class SongCreateRequest {

    private String title;
    private String singer;
    private UUID genreId;
    private String lyrics;
    private String path;
    private String image;
    private Integer duration;
    private Boolean status;
}
