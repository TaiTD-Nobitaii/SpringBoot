package com.dev.mymusic.service;


import com.dev.mymusic.dto.request.SongCreateRequest;
import com.dev.mymusic.dto.request.SongUpdateRequest;
import com.dev.mymusic.dto.response.SongResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface SongService {
    SongResponse createSong(SongCreateRequest songCreateRequest);

    SongResponse getSongById(UUID id);

    Page<SongResponse> getListSong(int page, int size, String search);

    Page<SongResponse> filterByGenre(int page, int size, String search);

    SongResponse updateSong(UUID id, SongUpdateRequest songUpdateRequest);
}
