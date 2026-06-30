package com.dev.mymusic.mapper;

import com.dev.mymusic.dto.response.PlaylistResponse;
import com.dev.mymusic.entity.Playlist;
import com.dev.mymusic.entity.PlaylistDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {

    @Mapping(source = "user.name", target = "nameUser")
    PlaylistResponse playlistToPlaylistResponse(Playlist playlist);


}
