package com.arun.practice.userservice.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginResponse {

    private String token;

}
