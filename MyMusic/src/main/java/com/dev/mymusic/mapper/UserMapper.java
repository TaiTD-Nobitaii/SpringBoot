package com.dev.mymusic.mapper;


import com.dev.mymusic.dto.request.UserCreateRequest;
import com.dev.mymusic.dto.response.UserResponse;
import com.dev.mymusic.entity.User;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "role.name", target =  "roleName")
    UserResponse userToUserResponse(User user); //output - input

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toUser(UserCreateRequest request);






}
