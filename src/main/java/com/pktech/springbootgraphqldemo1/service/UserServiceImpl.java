package com.pktech.springbootgraphqldemo1.service;

import com.pktech.springbootgraphqldemo1.dto.*;
import com.pktech.springbootgraphqldemo1.mapper.UserMapper;
import com.pktech.springbootgraphqldemo1.model.Role;
import com.pktech.springbootgraphqldemo1.model.User;
import com.pktech.springbootgraphqldemo1.repository.RoleRepository;
import com.pktech.springbootgraphqldemo1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public UserResponse createUser(UserRequest userRequest) {
        User user = UserMapper.toEntity(userRequest);
        if (userRequest.getRoles() != null) {
            Set<Role> roles = userRequest.getRoles().stream()
                    .map(roleName -> roleRepository.findByName(roleName)
                            .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
                    .collect(Collectors.toSet());
            user.setRoles(roles);
        }
        user = userRepository.save(user);
        return UserMapper.toResponse(user);
    }


    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setEmail(userRequest.getEmail());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setStatus(User.Status.valueOf(userRequest.getStatus().toUpperCase()));
        if (userRequest.getRoles() != null) {
            Set<Role> roles = userRequest.getRoles().stream()
                    .map(roleName -> roleRepository.findByName(roleName)
                            .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
                    .collect(Collectors.toSet());
            user.setRoles(roles);
        }
        user = userRepository.save(user);
        return UserMapper.toResponse(user);
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toResponse(user);
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserResponse assignRoleToUser(Long userId, String roleName) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Role role = roleRepository.findByName(roleName).orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
        user.getRoles().add(role);
        user = userRepository.save(user);
        return UserMapper.toResponse(user);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::toResponse)
                .collect(Collectors.toList());
    }
}
