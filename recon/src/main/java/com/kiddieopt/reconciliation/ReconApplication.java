package com.kiddieopt.reconciliation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ReconApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReconApplication.class, args);
	}

}
