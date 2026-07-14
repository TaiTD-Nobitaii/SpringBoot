package com.dev.mymusic.dto.request;

import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class AddSongToSongFavourite {
    private UUID idSong;
    private List<UUID> addSongId;
    private List<UUID> deleteSongId;

}
