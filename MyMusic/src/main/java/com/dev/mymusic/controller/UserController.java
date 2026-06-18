package com.dev.mymusic.controller;

import com.dev.mymusic.dto.request.UserCreateRequest;
import com.dev.mymusic.dto.request.UserRequest;
import com.dev.mymusic.dto.response.UserResponse;
import com.dev.mymusic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<Page<UserResponse>> getListUser(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size,
                                                          @RequestParam String search) {
        return ResponseEntity.ok(userService.getListUser(page, size, search));
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserCreateRequest request) {
        UserResponse response = userService.createUser(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID id, @RequestBody UserRequest request) {
        UserResponse response = userService.updateUser(id,request);
        return ResponseEntity.ok(response);
    }
}
