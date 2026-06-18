package com.dev.mymusic.service;


import com.dev.mymusic.dto.request.SongCreateRequest;
import com.dev.mymusic.dto.response.SongResponse;

import java.util.UUID;

public interface SongService {
    SongResponse createSong(SongCreateRequest songCreateRequest);

    SongResponse getSongById(UUID id);
}
