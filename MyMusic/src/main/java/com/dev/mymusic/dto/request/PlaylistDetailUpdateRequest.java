package com.dev.mymusic.dto.request;

import lombok.*;

import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistDetailUpdateRequest {
    private UUID playlistId;
    private UUID songId;
}
