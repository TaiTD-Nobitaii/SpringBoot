package com.dev.mymusic.dto.request;

import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class AddSongToMyFavourite {

    private UUID idUser;
    private List<UUID> addSongId;
}
