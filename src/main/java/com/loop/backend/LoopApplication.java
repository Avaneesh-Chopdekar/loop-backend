package com.loop.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LoopApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoopApplication.class, args);
	}

}
