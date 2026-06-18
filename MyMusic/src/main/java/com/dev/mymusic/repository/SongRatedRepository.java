package com.dev.mymusic.repository;

import com.dev.mymusic.entity.SongRated;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SongRatedRepository extends JpaRepository<SongRated, UUID> {
}