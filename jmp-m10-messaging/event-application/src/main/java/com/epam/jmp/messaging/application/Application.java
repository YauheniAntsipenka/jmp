package com.epam.jmp.messaging.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Application
 * Date: 04/01/2023
 *
 * @author Yauheni Antsipenka
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.epam.jmp.messaging.repository")
@EntityScan(basePackages = "com.epam.jmp.messaging.dto")
@ComponentScan(basePackages = "com.epam.jmp.messaging")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
