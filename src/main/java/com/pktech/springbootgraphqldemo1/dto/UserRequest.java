package com.pktech.springbootgraphqldemo1.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserRequest {

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String status;
    private Set<String> roles;


}
