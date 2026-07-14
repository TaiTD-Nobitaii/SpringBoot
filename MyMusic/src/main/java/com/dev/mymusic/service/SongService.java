package com.dev.mymusic.service;


import com.dev.mymusic.dto.request.*;
import com.dev.mymusic.dto.response.BaseResponse;
import com.dev.mymusic.dto.response.BaseResponsePaging;
import com.dev.mymusic.dto.response.SongResponse;
import com.dev.mymusic.entity.Song;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface SongService {
    SongResponse createSong(SongCreateRequest songCreateRequest);

    SongResponse getSongById(UUID id);

    Page<SongResponse> getListSong(int page, int size, String search);

    Page<SongResponse> filterByGenre(int page, int size, String search);

    BaseResponse<SongResponse> updateSong(UUID id, SongUpdateRequest songUpdateRequest);

    Void deleteSong(UUID id);

    BaseResponse<BaseResponsePaging<SongResponse>>findByGenreAndSearch(SongRequest songRequest);

    BaseResponse<List<Song>> addSongMyPlaylist(@RequestBody AddSongToPlayList request);

    BaseResponse<List<Song>> addSongFavourite(@RequestBody AddSongToSongFavourite addSongToSongFavourite);
}
