package com.dev.mymusic.controller;

import com.dev.mymusic.dto.request.*;
import com.dev.mymusic.dto.response.BaseResponse;
import com.dev.mymusic.dto.response.BaseResponsePaging;
import com.dev.mymusic.dto.response.ListSongMyFavouriteResponse;
import com.dev.mymusic.dto.response.SongResponse;
import com.dev.mymusic.entity.Song;
import com.dev.mymusic.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<BaseResponse<SongResponse>> updateSong(@PathVariable UUID id, @RequestBody SongUpdateRequest songUpdateRequest) {
        try {
            BaseResponse<SongResponse> response = songService.updateSong(id, songUpdateRequest);
            return ResponseEntity.status(response.getCode()).body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
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

    // Không chạy dc api này
    @PostMapping("/listSong")
    public ResponseEntity<BaseResponse<BaseResponsePaging<SongResponse>>> getAllSong(@RequestBody SongRequest songRequest)  {
        BaseResponse<BaseResponsePaging<SongResponse>> songResponse = songService.findByGenreAndSearch(songRequest);
        return ResponseEntity.ok(songResponse);

    }

    @PostMapping("/add-song-to-playlist")
    public ResponseEntity<BaseResponse<List<Song>>> addSongToPlaylist(@RequestBody AddSongToPlayList request) {
        return ResponseEntity.ok(songService.addSongMyPlaylist(request));
    }

//    @PostMapping("/add-song-to-favourite")
//    public ResponseEntity<BaseResponse<List<Song>>> addSongToSongFavourite(@RequestBody AddSongToSongFavourite addSongToSongFavourite){
//        return ResponseEntity.ok(songService.addSongFavourite(addSongToSongFavourite);
//    }

    @PostMapping("/add-song-to-myfavouirte")
    public ResponseEntity<BaseResponse<List<ListSongMyFavouriteResponse>>> addSongToMyFavourite(@RequestBody AddSongToMyFavourite request) {
        return ResponseEntity.ok(songService.addSongToMyFavourite(request));
    }
}
