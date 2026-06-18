package com.dev.mymusic.controller;

import com.dev.mymusic.dto.request.SongCreateRequest;
import com.dev.mymusic.dto.response.SongResponse;
import com.dev.mymusic.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/songs")
public class SongController {

    private final SongService songService;

    @PostMapping
    public ResponseEntity<SongResponse> createSong(@RequestBody SongCreateRequest request) {
        try {
            SongResponse response = songService.createSong(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<SongResponse> getSongById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(songService.getSongById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
