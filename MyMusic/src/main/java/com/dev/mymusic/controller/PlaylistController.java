package com.dev.mymusic.controller;

import com.dev.mymusic.dto.request.PlaylistRequest;
import com.dev.mymusic.dto.response.BaseResponse;
import com.dev.mymusic.dto.response.BaseResponsePaging;
import com.dev.mymusic.dto.response.PlaylistResponse;
import com.dev.mymusic.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/playlist")
public class PlaylistController {

    private final PlaylistService playlistService;

    @GetMapping
    public ResponseEntity<List<PlaylistResponse>> getAllPlaylist() {
        List<PlaylistResponse> playlists = playlistService.getAllPlaylists();
        return ResponseEntity.ok(playlists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaylistResponse> GetPlaylistById(@PathVariable UUID id) {
        try {
            PlaylistResponse playlistResponse = playlistService.getPlaylistById(id);
            return ResponseEntity.ok(playlistResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<PlaylistResponse>>UpdatePlaylist(@PathVariable UUID id, @RequestBody PlaylistRequest playlistRequest) {
        BaseResponse<PlaylistResponse> playlistResponse = playlistService.updatePlaylist(id, playlistRequest);
        return ResponseEntity.ok(playlistResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>>DeletePlaylist(@PathVariable UUID id) {
        BaseResponse<Void> response = playlistService.deletePlaylist(id);
        return ResponseEntity.ok(response);
    }
}
