package com.epam.jmp.kafka.client.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * WebMvcConfig
 * Date: 03/24/2023
 *
 * @author Yauheni Antsipenka
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.epam.com.jmp.kafka.client")
public class WebMvcConfig {
}
