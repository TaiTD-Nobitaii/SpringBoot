package com.dev.mymusic.service;

import com.dev.mymusic.dto.request.LoginRequest;
import com.dev.mymusic.dto.request.UserCreateRequest;
import com.dev.mymusic.dto.request.UserRequest;
import com.dev.mymusic.dto.response.BaseResponse;
import com.dev.mymusic.dto.response.LoginResponse;
import com.dev.mymusic.dto.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface UserService {

    BaseResponse<LoginResponse> login(LoginRequest loginRequest);

    Page<UserResponse>getListUser(int page, int size, String search);

    UserResponse createUser(UserCreateRequest userCreateRequest);

    UserResponse updateUser(UUID id, UserRequest userRequest);

}
