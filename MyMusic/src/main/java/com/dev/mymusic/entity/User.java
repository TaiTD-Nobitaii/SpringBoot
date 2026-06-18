    package com.dev.mymusic.entity;

    import jakarta.persistence.*;
    import lombok.*;

    import java.time.LocalDate;
    import java.time.LocalDateTime;
    import java.util.UUID;

    @Entity
    @Table(name = "USER")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public class User {

        @Id
        @GeneratedValue(strategy =  GenerationType.UUID)
        @Column(name = "id", updatable = false,nullable = false)
        private UUID id;

        @Column(name = "name", length = 40,nullable = false)
        private String name;

        @Column(name = "email", length = 40,nullable = false, unique = true)
        private String email;

        @Column(name = "password", length = 255,nullable = false)
        private String password;

        @Column(name = "birthday")
        private LocalDate birthday;

        @Column(name = "gender")
        private Boolean gender;

        @Column(name = "image", columnDefinition = "nvarchar(MAX)")
        private String image;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "idRole")
        private Role role;

        @Column(name = "phone", length = 20)
        private String phone;

        @Column(name = "status")
        private Boolean status;

        @Column(name = "createAt", nullable = false)
        private LocalDateTime createAt;

        @PrePersist
        protected void onCreate() {
            this.createAt = LocalDateTime.now();
        }
    }
