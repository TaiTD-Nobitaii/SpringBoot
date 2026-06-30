package com.dev.mymusic.service.impl;

import com.dev.mymusic.dto.request.SongCreateRequest;
import com.dev.mymusic.dto.request.SongRequest;
import com.dev.mymusic.dto.request.SongUpdateRequest;
import com.dev.mymusic.dto.response.BaseResponse;
import com.dev.mymusic.dto.response.BaseResponsePaging;
import com.dev.mymusic.dto.response.SongResponse;
import com.dev.mymusic.entity.Genre;
import com.dev.mymusic.entity.Song;
import com.dev.mymusic.mapper.SongMapper;
import com.dev.mymusic.repository.GenreRepository;
import com.dev.mymusic.repository.SongRepository;
import com.dev.mymusic.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final GenreRepository genreRepository;
    private final SongMapper songMapper;

    @Override
    public SongResponse createSong(SongCreateRequest songCreateRequest) {
        //Tại sao không phải ID?
        if (StringUtils.isEmpty(songCreateRequest.getTitle())) {
            throw new IllegalArgumentException("Title not valid");
        }
        Genre genre = genreRepository.findById(songCreateRequest.getGenreId()).orElse(null);
        if (genre == null) {
            throw new IllegalArgumentException("Genre not found");
        }
        Song song = songMapper.toSong(songCreateRequest);
        song.setGenre(genre);
        song.setTitle(songCreateRequest.getTitle());
        song.setSinger(songCreateRequest.getSinger());
        song.setImage(songCreateRequest.getImage());
        song.setPath(songCreateRequest.getPath());
        song.setDuration(songCreateRequest.getDuration());
        song.setLyrics(songCreateRequest.getLyrics());

        songRepository.save(song);
        return songMapper.songToSongResponse(song);
    }

    @Override
    public SongResponse getSongById(UUID id) {
        Optional<Song> song = songRepository.findById(id);

        if (song.isEmpty()) {
            throw new IllegalArgumentException("Song not found");
        }
        return songMapper.songToSongResponse(song.get());

//        return songMapper.songToSongResponse(songRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("Song not found")
//        ));
    }

    @Override
    public Page<SongResponse> getListSong(int page, int size, String search) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Song> songs = songRepository.findAll(pageable);
        if (search != null && !search.isEmpty()) {
            songs = songRepository.findByTitleOrLyricsContainingIgnoreCase(search, search, pageable);
        }
        return songs.map(songMapper::songToSongResponse);
    }

    @Override
    public Page<SongResponse> filterByGenre(int page, int size, String search) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Song> songs = songRepository.findAll(pageable);
        if (search != null && !search.isEmpty()) {
            songs = songRepository.filterGenreBySong(search, pageable);
        }
        return songs.map(songMapper::songToSongResponse);
    }

    @Override
    public BaseResponse<SongResponse> updateSong(UUID id, SongUpdateRequest songUpdateRequest) {
        try {
            Song song = songRepository.findById(id).orElse(null);
            if (song == null) {
                return BaseResponse.<SongResponse>builder()
                        .code(404)
                        .msg("Song not found")
                        .build();
            }
            Genre genre = genreRepository.findById(songUpdateRequest.getGenreId()).orElse(null);
            if (genre == null) {
                return BaseResponse.<SongResponse>builder()
                        .code(404)
                        .msg("Genre not found")
                        .build();
            }
            song.setTitle(songUpdateRequest.getTitle());
            song.setSinger(songUpdateRequest.getSinger());
            song.setGenre(genre);
            song.setSinger(songUpdateRequest.getSinger());
            song.setPath(songUpdateRequest.getPath());
            song.setDuration(songUpdateRequest.getDuration());
            songRepository.save(song);
            SongResponse songResponse = songMapper.songToSongResponse(song);

            return BaseResponse.<SongResponse>builder()
                    .code(200)
                    .msg("Song updated successfully")
                    .data(songResponse)
                    .build();
        } catch (Exception e) {
            return BaseResponse.<SongResponse>builder()
                    .code(500)
                    .msg("Something error")
                    .build();
        }
    }

    @Override
    public Void deleteSong(UUID id) {
        Song song = songRepository.findById(id).orElseThrow();
        songRepository.delete(song);
        song.setStatus(false);
        songRepository.save(song);
        return null;
    }

    @Override
    public BaseResponse<BaseResponsePaging<SongResponse>> findByGenreAndSearch(SongRequest songRequest) {
        BaseResponse<BaseResponsePaging<SongResponse>> response = new BaseResponse<>();
        try {
            Genre genre = genreRepository.findById(songRequest.getIdGenre()).orElse(null);
            if (genre == null) {
                response.setMsg("Genre not found");
                response.setCode(400);
                return response;
            }
            /*
            Những biến đầu vào của hàm repo findByGenreAndSearch nó sẽ tương ứng với các
            trường thông tin của SongRequest extends BaseRequestPaging
             */
            Integer offSet = (songRequest.getCurrentPages() - 1) * songRequest.getPageSize();
            List<Song> songs = songRepository.findByGenreAndSearch(
                    songRequest.getIdGenre(),
                    songRequest.getSearch(),
                    offSet,
                    songRequest.getPageSize());
            // sử dụng mapper để chuyển kiểu dữ liệu từ song qua songResponse
            List<SongResponse> listSongResponse = songs.stream().map(songMapper::songToSongResponse).toList();

            //Đếm tất cả các bản ghi có trên mọi phân trang
            Integer total = songRepository.countByGenre(songRequest.getIdGenre(), songRequest.getSearch());

            BaseResponsePaging<SongResponse> responsePaging = new BaseResponsePaging<>();
            responsePaging.setTotal(total);
            responsePaging.setData(listSongResponse);

            response.setMsg("Success");
            response.setCode(200);
            response.setData(responsePaging);
            return response;
        } catch (Exception e) {
            response.setMsg("Something error");
            response.setCode(400);
            e.printStackTrace();
            return response;
        }
    }
}
