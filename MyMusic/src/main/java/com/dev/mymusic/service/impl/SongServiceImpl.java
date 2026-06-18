package com.dev.mymusic.service.impl;

import com.dev.mymusic.dto.request.SongCreateRequest;
import com.dev.mymusic.dto.response.SongResponse;
import com.dev.mymusic.entity.Genre;
import com.dev.mymusic.entity.Song;
import com.dev.mymusic.mapper.SongMapper;
import com.dev.mymusic.repository.GenreRepository;
import com.dev.mymusic.repository.SongRepository;
import com.dev.mymusic.service.SongService;
import lombok.RequiredArgsConstructor;
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
        if(songRepository.findById(id).isEmpty()){
            throw new IllegalArgumentException("Song not found");
        }
        return songMapper.songToSongResponse(songRepository.findById(id).get());
    }
}
