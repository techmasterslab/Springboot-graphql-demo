package com.pktech.springbootgraphqldemo1.service;

import com.pktech.springbootgraphqldemo1.dto.UserRequest;
import com.pktech.springbootgraphqldemo1.dto.UserResponse;

import java.util.List;

public interface UserService {

    public UserResponse createUser(UserRequest userRequest);
    public UserResponse updateUser(Long id, UserRequest userRequest);
    public UserResponse getUserById(Long id);
    public void deleteUser(Long id);
    public UserResponse assignRoleToUser(Long userId, String roleName);
    public List<UserResponse> getAllUsers();
}
