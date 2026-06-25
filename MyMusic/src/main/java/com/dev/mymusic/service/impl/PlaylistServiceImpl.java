package com.dev.mymusic.service.impl;

import com.dev.mymusic.dto.response.PlaylistResponse;
import com.dev.mymusic.entity.Playlist;
import com.dev.mymusic.mapper.PlaylistMapper;
import com.dev.mymusic.repository.PlaylistRepository;
import com.dev.mymusic.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistMapper playlistMapper;
    private final PlaylistRepository playlistRepository;

    @Override
    public List<PlaylistResponse> getAllPlaylists() {
        List<Playlist> playLists = playlistRepository.findAll();
        return playLists.stream().map(playlistMapper::playlistToPlaylistResponse).toList();
    }

//    @Override
//    public PlaylistResponse getPlaylistById(UUID id) {
//        Playlist playlist = playlistRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Playlist not found"));
//
//        return playlistMapper.playlistToPlaylistResponse(playlist);
//    }


    @Override
    public PlaylistResponse getPlaylistById(UUID id) {
        if (playlistRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Playlist with id does not exist");
        }
        Playlist playlist = playlistRepository.findById(id).get();
        return playlistMapper.playlistToPlaylistResponse(playlist);
    }

}
