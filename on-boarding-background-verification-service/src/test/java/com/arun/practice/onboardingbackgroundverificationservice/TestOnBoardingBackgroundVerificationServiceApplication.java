package com.arun.practice.onboardingbackgroundverificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestOnBoardingBackgroundVerificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(OnBoardingBackgroundVerificationServiceApplication::main).with(TestOnBoardingBackgroundVerificationServiceApplication.class).run(args);
	}

}
