package com.dev.mymusic.controller;

import com.dev.mymusic.dto.request.LoginRequest;
import com.dev.mymusic.dto.response.BaseResponse;
import com.dev.mymusic.dto.response.LoginResponse;
import com.dev.mymusic.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<LoginResponse>> login (@Valid @RequestBody LoginRequest loginRequest){
        BaseResponse<LoginResponse> response= userService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}
