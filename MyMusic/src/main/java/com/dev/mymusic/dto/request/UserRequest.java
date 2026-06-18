package com.dev.mymusic.dto.request;

import com.dev.mymusic.entity.User;
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
public class UserRequest {
    private String name;
    private String password;
    private Boolean gender;
    private String phone;
    private LocalDate birthday;

}
