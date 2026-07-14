package com.dev.mymusic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListSongMyFavouriteResponse {
    private UUID id;
    private String title;
    private String singer;
    private String genreName;
}
