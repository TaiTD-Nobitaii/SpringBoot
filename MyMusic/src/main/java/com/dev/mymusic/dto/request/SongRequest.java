package com.dev.mymusic.dto.request;

import lombok.*;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SongRequest extends BaseRequestPaging {
    private UUID idGenre;
}
