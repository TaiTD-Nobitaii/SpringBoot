package com.dev.mymusic.dto.request;

import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class AddSongToPlayList {

    public UUID playlistId;
    public List<UUID> deleteSongId;
    public List<UUID> addSongId;
}
