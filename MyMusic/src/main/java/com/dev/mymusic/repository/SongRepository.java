package com.dev.mymusic.repository;

import com.dev.mymusic.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SongRepository extends JpaRepository<Song, UUID> {
}