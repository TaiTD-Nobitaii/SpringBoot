package com.dev.mymusic.mapper;

import com.dev.mymusic.dto.request.PlaylistDetailCreateRequest;
import com.dev.mymusic.dto.response.PlaylistDetailResponse;
import com.dev.mymusic.entity.PlaylistDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlaylistDetailMapper {

    @Mapping(source = "playlist.title", target =  "playlistTitle")
    @Mapping(source = "song.title", target =  "songTitle")
    @Mapping(source = "song.singer", target =  "singer")
    PlaylistDetailResponse playlistDetailResponse(PlaylistDetail playlistDetail);

    @Mapping(target = "id", ignore = true)
    PlaylistDetail toPlaylistDetail(PlaylistDetailCreateRequest playlistDetailCreateRequest);


}
