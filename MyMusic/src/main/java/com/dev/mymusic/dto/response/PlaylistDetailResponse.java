package com.dev.mymusic.dto.response;

import lombok.*;

import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistDetailResponse {
    private String playlistTitle;
    private String songTitle;
    private String singer;
}
