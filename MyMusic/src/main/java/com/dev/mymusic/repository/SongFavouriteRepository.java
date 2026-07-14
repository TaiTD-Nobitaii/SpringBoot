package com.dev.mymusic.repository;

import com.dev.mymusic.dto.response.ListSongMyFavouriteResponse;
import com.dev.mymusic.entity.Song;
import com.dev.mymusic.entity.SongFavourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface SongFavouriteRepository extends JpaRepository<SongFavourite, UUID> {

    @Query("""
                       select s  from SongFavourite sf
                                                             join Song s on s.id =sf.song.id
                                                             join Genre g on g.id = s.idGenre
                       where sf.user.id = :idUser
            """)
    List<Song> getSongFavourite(@Param("idUser") UUID idUser);

//    @Transactional
//    @Modifying
//    @Query("delete from SongFavourite where song.id in :ids")
//    void removeAllSongFavouriteById(List<UUID> ids);
}