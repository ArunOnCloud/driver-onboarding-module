package com.arun.practice.onboardingdocumentscollectionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestOnBoardingDocumentsCollectionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(OnBoardingDocumentsCollectionServiceApplication::main).with(TestOnBoardingDocumentsCollectionServiceApplication.class).run(args);
	}

}
