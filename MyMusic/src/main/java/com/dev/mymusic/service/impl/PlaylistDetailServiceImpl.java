package com.dev.mymusic.service.impl;

import com.dev.mymusic.dto.request.PlaylistDetailCreateRequest;
import com.dev.mymusic.dto.request.PlaylistDetailUpdateRequest;
import com.dev.mymusic.dto.response.BaseResponse;
import com.dev.mymusic.dto.response.PlaylistDetailResponse;
import com.dev.mymusic.entity.Playlist;
import com.dev.mymusic.entity.PlaylistDetail;
import com.dev.mymusic.entity.Song;
import com.dev.mymusic.mapper.PlaylistDetailMapper;
import com.dev.mymusic.repository.PlaylistDetailRepository;
import com.dev.mymusic.repository.PlaylistRepository;
import com.dev.mymusic.repository.SongRepository;
import com.dev.mymusic.service.PlaylistDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlaylistDetailServiceImpl implements PlaylistDetailService {

    private final PlaylistDetailRepository playlistDetailRepository;
    private final PlaylistRepository playlistRepository;
    private final SongRepository songRepository;
    private final PlaylistDetailMapper playlistDetailMapper;

    @Override
    public BaseResponse<PlaylistDetailResponse> getByid(UUID id) {
        BaseResponse<PlaylistDetailResponse> response = new BaseResponse<>();
        try {
            PlaylistDetail playlistDetail = playlistDetailRepository.findById(id).orElse(null);
            if (playlistDetail == null) {
                response.setMsg("PlaylistDetail not found");
                response.setCode(400);
                return response;
            }
            PlaylistDetailResponse playlistDetailResponse = playlistDetailMapper.playlistDetailResponse(playlistDetail);
            return BaseResponse.<PlaylistDetailResponse>builder()
                    .msg("PlaylistDetail: " + id)
                    .code(200)
                    .data(playlistDetailResponse)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BaseResponse<PlaylistDetailResponse> create(PlaylistDetailCreateRequest playlistDetailCreateRequest) {
        try {
            Playlist playlist = playlistRepository.findById(playlistDetailCreateRequest.getPlaylistId()).orElse(null);
            if (playlist == null) {
                return BaseResponse.<PlaylistDetailResponse>builder()
                        .msg("Create playlist not found")
                        .code(400)
                        .build();
            }
            Song song = songRepository.findById(playlistDetailCreateRequest.getSongId()).orElse(null);
            if (playlist == null) {
                return BaseResponse.<PlaylistDetailResponse>builder()
                        .msg("Create song not found")
                        .code(400)
                        .build();
            }
            //Vì cái này đang là playlistDetail nên cần map sang playlistDetailCreateRequest
            PlaylistDetail playlistDetail = playlistDetailMapper.toPlaylistDetail(playlistDetailCreateRequest);

            //sau khi map qua thì mới create
            playlistDetail.setPlaylist(playlist);
            playlistDetail.setSong(song);
            playlistDetailRepository.save(playlistDetail);


            // Cần hỏi a Thiện chỗ này
//            PlaylistDetailResponse playlistDetailResponse = playlistDetailMapper.playlistDetailResponse(playlistDetail);

            //Kiểu dữ liệu PlaylistDetailResponse từ base nên phải map lại để trả ra data
            PlaylistDetailResponse response = playlistDetailMapper.playlistDetailResponse(playlistDetail);
            return BaseResponse.<PlaylistDetailResponse>builder()
                    .msg("PlaylistDetail create successfully")
                    .data(response)
                    .code(200)
                    .build();
        } catch (Exception e) {
            return BaseResponse.<PlaylistDetailResponse>builder()
                    .msg("Something error")
                    .code(200)
                    .build();
        }
    }

    @Override
    public BaseResponse<PlaylistDetailResponse> update(UUID id, PlaylistDetailUpdateRequest playlistDetailUpdateRequest) {
        try {
            PlaylistDetail playlistDetail = playlistDetailRepository.findById(id).orElse(null);
            if(playlistDetail == null){
                return BaseResponse.<PlaylistDetailResponse>builder()
                        .msg("Update error")
                        .code(400)
                        .build();
            }

            // Tại sao findById của playlist .get thì truyền dc ID vào còn .orElse thì ko dc?
//            Playlist playlist = playlistRepository.findById(id).orElse(null);
            Playlist playlist = playlistRepository.findById(id).get();
            // Nhưng Song thì được
            Song song = songRepository.findById(id).orElse(null);

            playlistDetail.setPlaylist(playlist);
            playlistDetail.setSong(song);
            playlistDetailRepository.save(playlistDetail);
            PlaylistDetailResponse response = playlistDetailMapper.playlistDetailResponse(playlistDetail);
            return BaseResponse.<PlaylistDetailResponse>builder()
                    .msg("Update playlistDetail successfully")
                    .code(200)
                    .data(response)
                    .build();
        } catch (Exception e) {
            return BaseResponse.<PlaylistDetailResponse>builder()
                    .msg("Something error")
                    .code(400)
                    .build();
        }
    }
}
