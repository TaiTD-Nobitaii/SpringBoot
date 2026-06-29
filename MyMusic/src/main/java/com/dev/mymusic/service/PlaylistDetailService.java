package com.dev.mymusic.service;

import com.dev.mymusic.dto.request.PlaylistDetailCreateRequest;
import com.dev.mymusic.dto.response.BaseResponse;
import com.dev.mymusic.dto.response.PlaylistDetailResponse;
import com.dev.mymusic.dto.response.PlaylistResponse;

import java.util.UUID;

public interface PlaylistDetailService {
    BaseResponse<PlaylistDetailResponse> getByid(UUID id);

    BaseResponse<PlaylistDetailResponse> create( PlaylistDetailCreateRequest playlistDetailCreateRequest);
}
