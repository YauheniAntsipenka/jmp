package com.epam.jmp.messaging.application.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * WebMvcConfig
 * Date: 04/01/2023
 *
 * @author Yauheni Antsipenka
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.epam.jmp.messaging.rest.controller")
public class WebMvcConfig {
}
