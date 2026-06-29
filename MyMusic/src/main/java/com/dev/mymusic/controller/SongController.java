package com.dev.mymusic.controller;

import com.dev.mymusic.dto.request.SongCreateRequest;
import com.dev.mymusic.dto.request.SongRequest;
import com.dev.mymusic.dto.request.SongUpdateRequest;
import com.dev.mymusic.dto.response.BaseResponse;
import com.dev.mymusic.dto.response.BaseResponsePaging;
import com.dev.mymusic.dto.response.SongResponse;
import com.dev.mymusic.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @GetMapping("/list")
    public ResponseEntity<Page<SongResponse>> getListSong(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size,
                                                          @RequestParam String search) {
        try {
            return ResponseEntity.ok(songService.getListSong(page, size, search));

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Page<SongResponse>> filterByGenre(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size,
                                                            @RequestParam String search) {
        try {
            return ResponseEntity.ok(songService.filterByGenre(page, size, search));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<SongResponse> updateSong(@PathVariable UUID id, @RequestBody SongUpdateRequest songUpdateRequest) {
        try {
            SongResponse response = songService.updateSong(id, songUpdateRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable UUID id) {
        try {
            songService.deleteSong(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/listSong")
    public ResponseEntity<BaseResponse<BaseResponsePaging<SongResponse>>> getAllSong(@RequestBody SongRequest songRequest)  {
        BaseResponse<BaseResponsePaging<SongResponse>> songResponse = songService.findByGenreAndSearch(songRequest);
        return ResponseEntity.ok(songResponse);

    }

}
