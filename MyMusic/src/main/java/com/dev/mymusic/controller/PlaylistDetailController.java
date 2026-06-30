package com.dev.mymusic.controller;

import com.dev.mymusic.dto.request.PlaylistDetailCreateRequest;
import com.dev.mymusic.dto.request.PlaylistDetailUpdateRequest;
import com.dev.mymusic.dto.response.BaseResponse;
import com.dev.mymusic.dto.response.BaseResponsePaging;
import com.dev.mymusic.dto.response.PlaylistDetailResponse;
import com.dev.mymusic.service.PlaylistDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/playlist_detail")
public class PlaylistDetailController {

    private final PlaylistDetailService playlistDetailService;

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<PlaylistDetailResponse>> getPlaylistDetailByid(@PathVariable UUID id) {
        BaseResponse<PlaylistDetailResponse> response = playlistDetailService.getByid(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<BaseResponse<PlaylistDetailResponse>> createPlaylistDetail(@RequestBody PlaylistDetailCreateRequest playlistDetailCreateRequest) {
        BaseResponse<PlaylistDetailResponse> response = playlistDetailService.create(playlistDetailCreateRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<PlaylistDetailResponse>> updatePlaylistDetailById(@PathVariable UUID id, @RequestBody PlaylistDetailUpdateRequest playlistDetailUpdateRequest) {
        BaseResponse<PlaylistDetailResponse> response = playlistDetailService.update(id, playlistDetailUpdateRequest);
        return ResponseEntity.ok(response);
    }

}
