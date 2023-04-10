package com.epam.jmp.spring.mvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * WebConfig
 * Date: 03/04/2023
 *
 * @author Yauheni Antsipenka
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.epam.jmp.spring.mvc")
@ImportResource("classpath:/context/ApplicationContext.xml")
public class WebConfig {
}
