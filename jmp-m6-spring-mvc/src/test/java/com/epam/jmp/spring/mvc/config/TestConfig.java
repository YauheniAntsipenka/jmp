package com.epam.jmp.spring.mvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * TestConfig
 * Date: 03/06/2023
 *
 * @author Yauheni Antsipenka
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.epam.jmp.spring.mvc.controller", "com.epam.jmp.spring.mvc.service",
    "com.epam.jmp.spring.mvc.facade", "com.epam.jmp.spring.mvc.dao"})
@ImportResource("classpath:/context/ApplicationContext.xml")
public class TestConfig {
}
