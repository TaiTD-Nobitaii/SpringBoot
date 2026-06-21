package com.dev.mymusic.service;

import com.dev.mymusic.dto.response.PlaylistResponse;

import java.util.List;
import java.util.UUID;

public interface PlaylistService {
    List<PlaylistResponse> getAllPlaylists();

    PlaylistResponse getPlaylistById(UUID id);
}
