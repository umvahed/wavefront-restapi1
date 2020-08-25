package com.wavefront;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the entry point of the application.
 * Spring Boot will automatically call this file
 * upon running
 *
 */
@SpringBootApplication
public class WavefrontRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(WavefrontRestApplication.class, args);
	}

}
