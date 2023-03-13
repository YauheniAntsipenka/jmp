package com.epam.jmp.jmpm7springboot;

import com.epam.jmp.jmpm7springboot.domain.Person;
import com.epam.jmp.jmpm7springboot.repository.PersonRepository;
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
	public CommandLineRunner CommandLineRunnerBean(PersonRepository repository) {
		return (args) -> {
			repository.saveAll(PersonInitializer.init());
			LOGGER.info(String.valueOf(repository.findAll()));
			Person person = repository.findById(1);
			person.setFirstName("newName");
			repository.save(person);
			LOGGER.info(String.valueOf(repository.findAll()));
		};
	}
}
