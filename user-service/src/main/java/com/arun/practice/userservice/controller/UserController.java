package com.arun.practice.userservice.controller;

import com.arun.practice.userservice.model.User;
import com.arun.practice.userservice.request.UserLoginRequest;
import com.arun.practice.userservice.response.UserLoginResponse;
import com.arun.practice.userservice.response.UserResponse;
import com.arun.practice.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/")
public class UserController {

    @GetMapping("")
    public String hello(){
        return "Hello";
    }

    @Autowired
    UserService userService;

    @PostMapping("login")
    public ResponseEntity<UserLoginResponse> login(UserLoginRequest userLoginRequest){

        UserLoginResponse userLoginResponse = UserLoginResponse.builder().token("random-token").build();
        ResponseEntity<UserLoginResponse> response = new ResponseEntity<>(userLoginResponse, HttpStatus.OK);
        return response;
    }

    @GetMapping("users")
    public List<User> getAllUsers(){
      return  userService.listUser();
    }
}
