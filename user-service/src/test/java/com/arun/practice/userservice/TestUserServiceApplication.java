package com.arun.practice.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(UserServiceApplication::main).with(TestUserServiceApplication.class).run(args);
	}

}
