package com.dev.mymusic.service.impl;

import com.dev.mymusic.dto.request.SongCreateRequest;
import com.dev.mymusic.dto.request.SongUpdateRequest;
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
        if (songRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Song not found");
        }
        return songMapper.songToSongResponse(songRepository.findById(id).get());
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
    public SongResponse updateSong(UUID id, SongUpdateRequest songUpdateRequest) {
        Song song = songRepository.findById(id).orElseThrow();
        Genre genre = genreRepository.findById(songUpdateRequest.getGenreId()).orElseThrow();
        song.setTitle(songUpdateRequest.getTitle());
        song.setSinger(songUpdateRequest.getSinger());
        song.setGenre(genre);
        song.setSinger(songUpdateRequest.getSinger());
        song.setPath(songUpdateRequest.getPath());
        song.setDuration(songUpdateRequest.getDuration());
        songRepository.save(song);
        return songMapper.songToSongResponse(song);
    }
}
