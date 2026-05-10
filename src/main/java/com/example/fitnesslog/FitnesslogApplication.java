package com.example.fitnesslog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FitnesslogApplication {

	public static void main(String[] args) {
		System.out.println(System.getenv("MONGO_URI"));
		SpringApplication.run(FitnesslogApplication.class, args);
	}

}
