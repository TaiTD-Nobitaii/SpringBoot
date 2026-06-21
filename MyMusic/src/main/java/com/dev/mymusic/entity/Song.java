package com.dev.mymusic.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "SONG")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Song {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    @Column(name = "id", updatable = false,nullable = false)
    private UUID id;

    @Column(name = "title", length = 255,nullable = false)
    private String title;

    @Column(name = "singer", columnDefinition = "nvarchar(MAX)")
    private String singer;

//    @Id
//    @GeneratedValue(strategy =  GenerationType.UUID)
//    @Column(name = "idGenre", updatable = false,nullable = false)
//    private UUID idGenre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGenre")
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idArtist")
    private Artist artist;

    @Column(name = "lyrics", columnDefinition = "nvarchar(MAX)")
    private String lyrics;

    @Column(name = "image", length = 255)
    private String image;

    @Column(name = "path", length = 255)
    private String path;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "createAt", nullable = false)
    private LocalDateTime createAt;

    @PrePersist
    protected void onCreate() {
        this.createAt = LocalDateTime.now();
    }


}
