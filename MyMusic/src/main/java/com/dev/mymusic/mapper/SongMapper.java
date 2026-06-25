package com.dev.mymusic.mapper;

import com.dev.mymusic.dto.request.SongCreateRequest;
import com.dev.mymusic.dto.response.SongResponse;
import com.dev.mymusic.entity.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SongMapper {

    @Mapping(source = "genre.name", target = "genreName")
    SongResponse songToSongResponse(Song song);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "genre", ignore = true)
    @Mapping(target = "duration", ignore = true)
    @Mapping(target = "quantity", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    Song toSong(SongCreateRequest songCreateRequest);
}
