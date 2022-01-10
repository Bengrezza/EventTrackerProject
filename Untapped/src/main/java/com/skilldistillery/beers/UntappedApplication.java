package com.skilldistillery.beers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.skilldistillery.untapped")
@SpringBootApplication
public class UntappedApplication {

	public static void main(String[] args) {
		SpringApplication.run(UntappedApplication.class, args);
	}

}
