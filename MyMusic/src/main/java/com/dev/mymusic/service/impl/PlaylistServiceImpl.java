package com.dev.mymusic.service.impl;

import com.dev.mymusic.dto.request.PlaylistRequest;
import com.dev.mymusic.dto.response.BaseResponse;
import com.dev.mymusic.dto.response.PlaylistResponse;
import com.dev.mymusic.entity.Playlist;
import com.dev.mymusic.entity.User;
import com.dev.mymusic.mapper.PlaylistMapper;
import com.dev.mymusic.repository.PlaylistRepository;
import com.dev.mymusic.repository.UserRepository;
import com.dev.mymusic.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistMapper playlistMapper;
    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;

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

    @Override
    public BaseResponse<PlaylistResponse> updatePlaylist(UUID id, PlaylistRequest playlistRequest) {
        BaseResponse<PlaylistResponse> response = new BaseResponse<>();
        try {
            Playlist playlist = playlistRepository.findById(id).get();
//            Optional<User> user = userRepository.findById(playlistRequest.getUserId());
            if (playlist == null) {
                response.setMsg("Playlist with id does not exist");
                response.setCode(400);
                return response;
            }
            if (playlistRequest.getTitle() != null && !playlistRequest.getTitle().isBlank()) {
                playlist.setTitle(playlistRequest.getTitle());
            }

            // 3. Cập nhật user nếu có truyền userId
            if (playlistRequest.getUserId() != null) {
                User user = userRepository.findById(playlistRequest.getUserId())
                        .orElseThrow(() -> new RuntimeException("User not found with id: " + playlistRequest.getUserId()));
                playlist.setUser(user);
            }
            playlistRepository.save(playlist);
            PlaylistResponse playlistResponse = playlistMapper.playlistToPlaylistResponse(playlist);

            return BaseResponse.<PlaylistResponse>builder()
                    .code(200)
                    .msg("Update playlist successfully")
                    .data(playlistResponse)
                    .build();

        } catch (Exception e) {
            return BaseResponse.<PlaylistResponse>builder().
                    msg("Something error").
                    code(400).
                    build();
        }
    }

    @Override
    public BaseResponse<Void> deletePlaylist(UUID id) {
        BaseResponse<Void> response = new BaseResponse<>();
        try {
            Playlist playlist = playlistRepository.findById(id)
                    .orElse(null);
            if (playlist == null) {
                response.setMsg("Playlist with id does not exist");
                response.setCode(400);
                return response;
            }
            playlistRepository.delete(playlist);
            return BaseResponse.<Void>builder()
                    .msg("Delete playlist successfully")
                    .code(200)
                    .build();
        } catch (Exception e) {
            return BaseResponse.<Void>builder()
                    .msg("Something error")
                    .code(400)
                    .build();
        }
    }

}
