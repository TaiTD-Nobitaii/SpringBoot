package com.dev.mymusic.repository;

import com.dev.mymusic.dto.response.PlaylistResponse;
import com.dev.mymusic.entity.Playlist;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlaylistRepository extends JpaRepository<Playlist, UUID> {
    List<Playlist> id(UUID id);

}