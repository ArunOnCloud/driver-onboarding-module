package com.arun.practice.userservice.service;

import com.arun.practice.userservice.model.User;
import com.arun.practice.userservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    public List<User> listUser(){
        List<User> users= List.of(User.builder().name("firstName").email("firstEmail").build(),
                User.builder().name("firstNameTwo").email("abc@arun.com").build());
        return users;

    }
}
