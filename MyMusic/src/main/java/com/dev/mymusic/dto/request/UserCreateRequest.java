package com.dev.mymusic.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateRequest {
    private String name;
    private String email;
    private String password;
    private LocalDate birthday;
    private Boolean gender;
    private String image;
    private String phone;
    private UUID roleId;
}
