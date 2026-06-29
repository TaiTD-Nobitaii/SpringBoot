package com.dev.mymusic.repository;

import com.dev.mymusic.entity.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    @Query("""
            select s
            from Song s
            join Genre g
                  on s.idGenre = g.id
            where (:genre is null  or g.id = :genre)  
                  and (s.title like concat('%', :search,'%') 
                  or s.lyrics like concat('%', :search,'%'))
            order by s.title
            offset :offset rows
            fetch next :pageSize rows only
            """)
    List<Song> findByGenreAndSearch(@Param("genre") UUID genre, @Param("search") String search, @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    @Query("""
            select count(s.id)
            from Song s
            join Genre g
                    on s.idGenre = g.id
            where (:genre is null  or g.id = :genre)  
                  and (s.title like concat('%', :search,'%') 
                  or s.lyrics like concat('%', :search,'%'))
        """)
    Integer countByGenre(UUID genre, @Param("search") String search);

}