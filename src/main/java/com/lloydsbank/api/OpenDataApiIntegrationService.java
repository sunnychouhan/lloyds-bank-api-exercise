package com.lloydsbank.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.lloydsbank.api")
public class OpenDataApiIntegrationService {

	public static void main(String[] args) {
		SpringApplication.run(OpenDataApiIntegrationService.class, args);
	}

}
