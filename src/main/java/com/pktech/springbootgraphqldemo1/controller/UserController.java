package com.pktech.springbootgraphqldemo1.controller;

import com.pktech.springbootgraphqldemo1.dto.*;
import com.pktech.springbootgraphqldemo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @QueryMapping
    public UserResponse getUserById(@Argument String id) {
        return userService.getUserById(Long.parseLong(id));
    }

    @MutationMapping
    public UserResponse createUser(@Argument UserRequest userRequest) {
        return userService.createUser(userRequest);
    }
    @MutationMapping
    public UserResponse updateUser(@Argument String id, @Argument UserRequest userRequest) {
        return userService.updateUser(Long.parseLong(id), userRequest);
    }

    @MutationMapping
    public String deleteUser(@Argument String id) {
        userService.deleteUser(Long.parseLong(id));
        return "User deleted successfully";
    }

    @MutationMapping
    public UserResponse assignRoleToUser(@Argument String userId, @Argument String roleName) {
        return userService.assignRoleToUser(Long.parseLong(userId), roleName);
    }

    @QueryMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }
}
