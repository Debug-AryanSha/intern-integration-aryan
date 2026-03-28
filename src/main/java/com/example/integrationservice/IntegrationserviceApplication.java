package com.example.integrationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class IntegrationserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegrationserviceApplication.class, args);
	}

}
