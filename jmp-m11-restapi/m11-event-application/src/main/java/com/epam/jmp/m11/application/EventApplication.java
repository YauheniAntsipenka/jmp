package com.epam.jmp.m11.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * EventApplication
 * Date: 04/10/2023
 *
 * @author Yauheni Antsipenka
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.epam.jmp.m11.service.api.repository")
@EntityScan(basePackages = "com.epam.jmp.m11.dto")
@ComponentScan(basePackages = "com.epam.jmp.m11")
public class EventApplication {
    public static void main(String[] args) {
        SpringApplication.run(EventApplication.class);
    }
}
