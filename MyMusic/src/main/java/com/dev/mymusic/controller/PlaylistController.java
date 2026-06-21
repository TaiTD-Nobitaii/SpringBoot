package com.dev.mymusic.controller;

import com.dev.mymusic.dto.response.PlaylistResponse;
import com.dev.mymusic.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
