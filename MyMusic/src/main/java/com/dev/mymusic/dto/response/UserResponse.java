package com.dev.mymusic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private UUID id;
    private String name;
    private String email;
    private LocalDate birthday;
    private Boolean gender;
    private String image;
    private String roleName;
    private String phone;
    private Boolean status;
    private LocalDateTime createAt;
}
