package com.dev.mymusic.repository;

import com.dev.mymusic.entity.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface SongRepository extends JpaRepository<Song, UUID> {


    Page<Song> findByTitleOrLyricsContainingIgnoreCase(String title, String lyrics, Pageable pageable);

    @Query("""
            select s
               from Song s
               left join s.genre g
            where lower(g.name) LIKE lower(concat('%', :search, '%'))
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

    @Query("  Select s from Song s join PlaylistDetail p on s.id = p.song.id \n" +
            "  where p.playlist.id = :idPlayList ")
    List<Song> getMyPlaylist(@Param("idPlaylist") UUID idPlaylist);

    @Transactional
    @Modifying
    @Query("delete from PlaylistDetail where song.id in :ids")
    void removeAllSongById(List<UUID> ids);

}