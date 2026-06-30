package com.dev.mymusic.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Entity
@Table(name = "PLAYLIST_DETAIL")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PlaylistDetail {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    @Column(name = "id", updatable = false,nullable = false)
    private UUID id;
    
    @Column(name = "idPlaylist",nullable = false)
    private UUID idPlaylist;

    @Column(name = "idSong",nullable = false)
    private UUID idSong;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPlaylist", nullable = false, updatable = false)
    private Playlist playlist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSong",  nullable = false, updatable = false)
    private Song song;
}
