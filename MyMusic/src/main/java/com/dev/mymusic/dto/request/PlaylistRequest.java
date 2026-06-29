package com.dev.mymusic.dto.request;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlaylistRequest {
    private String title;
    private UUID userId;

}
