package com.arun.practice.onboardingdeviceshippingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestOnBoardingDeviceShippingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(OnBoardingDeviceShippingServiceApplication::main).with(TestOnBoardingDeviceShippingServiceApplication.class).run(args);
	}

}
