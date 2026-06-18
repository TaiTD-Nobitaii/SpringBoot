package com.dev.mymusic.dto.request;

import com.dev.mymusic.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class SongUpdateRequest {
    private String title;
    private String singer;
    private UUID genreId;
    private String path;
    private Integer duration;
}
