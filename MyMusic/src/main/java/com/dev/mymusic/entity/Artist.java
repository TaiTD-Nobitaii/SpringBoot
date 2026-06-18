package com.dev.mymusic.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ARTIST")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Artist {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    @Column(name = "id", updatable = false,nullable = false)
    private UUID id;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "createAt", nullable = false)
    private LocalDateTime createAt;





    @Column(name = "name", length = 30,nullable = false)
    private String name;

    @Column(name = "country", length = 30)
    private String country;

    @Column(name = "short_description", columnDefinition = "nvarchar(MAX)")
    private String short_description;

    @Column(name = "image", length = 255)
    private String image;



    @PrePersist
    protected void onCreate() {
        this.createAt = LocalDateTime.now();
    }
}
