package com.dev.mymusic.service.impl;

import com.dev.mymusic.dto.request.LoginRequest;
import com.dev.mymusic.dto.request.UserCreateRequest;
import com.dev.mymusic.dto.request.UserRequest;
import com.dev.mymusic.dto.response.BaseResponse;
import com.dev.mymusic.dto.response.LoginResponse;
import com.dev.mymusic.dto.response.UserResponse;
import com.dev.mymusic.entity.Role;
import com.dev.mymusic.entity.User;
import com.dev.mymusic.mapper.UserMapper;
import com.dev.mymusic.repository.RoleRepository;
import com.dev.mymusic.repository.UserRepository;
import com.dev.mymusic.security.JwtTokenProvider;
import com.dev.mymusic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    public BaseResponse<LoginResponse> login(LoginRequest loginRequest) {
        BaseResponse<LoginResponse> response = new BaseResponse<>();
        try {
            User user = userRepository.findByEmail(loginRequest.getEmail()).orElse(null);
            if (user == null) {
                response.setMsg("User not found");
                response.setCode(400);
                return response;
            }
            Boolean checkPassword = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
            if (checkPassword) {
                String token = jwtTokenProvider.generateToken(user.getEmail());
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setToken(token);
                loginResponse.setUuid(user.getId());
                loginResponse.setEmail(user.getEmail());
                loginResponse.setImage(user.getImage());
                response.setData(loginResponse);
                response.setCode(200);
                response.setMsg("Login Success");
                return response;
            } else {
                response.setMsg("Wrong Password");
                response.setCode(400);
                return response;
            }
        } catch (Exception e) {
            response.setMsg("Something error");
            response.setCode(400);
            e.printStackTrace();
            return response;

        }
    }

    @Override
    public Page<UserResponse> getListUser(int page, int size, String search) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<User> users = userRepository.findAll(pageable);
            if (search != null && !search.isEmpty()) {
                users = userRepository.findByNameContainingIgnoreCase(search, pageable);
            }
            return users.map(userMapper::userToUserResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserResponse createUser(UserCreateRequest userCreateRequest) {
        try {
            if (StringUtils.isEmpty(userCreateRequest.getEmail())) {
                throw new Exception("Invalid email");
            }
            Role role = roleRepository.findById(userCreateRequest.getRoleId()).orElseThrow();
            User user = userMapper.toUser(userCreateRequest);

            user.setRole(role);
            // Mã hoá mật khẩu
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setStatus(true);
            User savedUser = userRepository.save(user);
            return userMapper.userToUserResponse(savedUser);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserResponse updateUser(UUID id, UserRequest userRequest) {
        try {
            User user = userRepository.findById(id).orElseThrow();
            user.setPassword(userRequest.getPassword());
            user.setName(userRequest.getName());
            user.setGender(userRequest.getGender());
            user.setBirthday(userRequest.getBirthday());
            user.setPhone(userRequest.getPhone());
            userRepository.save(user);
            return userMapper.userToUserResponse(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
