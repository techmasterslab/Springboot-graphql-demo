package com.pktech.springbootgraphqldemo1.mapper;

import com.pktech.springbootgraphqldemo1.dto.UserRequest;
import com.pktech.springbootgraphqldemo1.dto.UserResponse;
import com.pktech.springbootgraphqldemo1.model.User;
import com.pktech.springbootgraphqldemo1.model.Role;

import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {

    public static User toEntity(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setEmail(userRequest.getEmail());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setStatus(User.Status.valueOf(userRequest.getStatus().toUpperCase()));
        return user;
    }

    public static UserResponse toResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setUpdatedAt(user.getUpdatedAt());
        userResponse.setStatus(user.getStatus().name().toLowerCase());
        userResponse.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
        return userResponse;
    }
}
