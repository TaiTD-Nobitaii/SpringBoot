package com.dev.mymusic.repository;

import com.dev.mymusic.entity.PlaylistDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlaylistDetailRepository extends JpaRepository<PlaylistDetail, UUID> {
}