package com.dev.mymusic.repository;

import com.dev.mymusic.entity.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface SongRepository extends JpaRepository<Song, UUID> {

    Page<Song> findByTitleOrLyricsContainingIgnoreCase(String title, String lyrics, Pageable pageable);

    @Query("""
            SELECT s
               FROM Song s
               LEFT JOIN s.genre g
            WHERE LOWER(g.name) LIKE LOWER(CONCAT('%', :search, '%'))
            """)
    Page<Song> filterGenreBySong(String search, Pageable pageable);

}