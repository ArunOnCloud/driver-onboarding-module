package com.arun.practice.userservice.request;


import lombok.Data;

@Data
public class UserLoginRequest {
    private String email;
    private String password;
}
