package com.epam.jmp.jmpm7springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JmpM7SpringBootApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(JmpM7SpringBootApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JmpM7SpringBootApplication.class, args);
	}

	@Bean
	public CommandLineRunner CommandLineRunnerBean() {
		return (args) -> LOGGER.info("Hello, World!");
	}
}
