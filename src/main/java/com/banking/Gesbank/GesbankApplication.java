package com.banking.Gesbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GesbankApplication {

	public static void main(String[] args) {
		SpringApplication.run(GesbankApplication.class, args);
	}

}
