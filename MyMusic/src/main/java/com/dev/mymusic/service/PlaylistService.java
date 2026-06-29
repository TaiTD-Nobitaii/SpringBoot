package com.dev.mymusic.service;

import com.dev.mymusic.dto.request.PlaylistRequest;
import com.dev.mymusic.dto.response.BaseResponse;
import com.dev.mymusic.dto.response.BaseResponsePaging;
import com.dev.mymusic.dto.response.PlaylistResponse;

import java.util.List;
import java.util.UUID;

public interface PlaylistService {
    List<PlaylistResponse> getAllPlaylists();

    PlaylistResponse getPlaylistById(UUID id);

    BaseResponse<PlaylistResponse> updatePlaylist(UUID id, PlaylistRequest playlistRequest);

    BaseResponse<Void> deletePlaylist(UUID id);
}
