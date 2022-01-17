package com.skilldistillery.beers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@EntityScan("com.skilldistillery.untapped")
@SpringBootApplication
public class UntappedApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(UntappedApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(UntappedApplication.class, args);
	}

}
