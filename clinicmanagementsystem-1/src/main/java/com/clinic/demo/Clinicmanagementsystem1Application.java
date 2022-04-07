package com.clinic.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class Clinicmanagementsystem1Application {

	public static void main(String[] args) {
		SpringApplication.run(Clinicmanagementsystem1Application.class, args);
	}

}
