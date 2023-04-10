package com.epam.jmp.m11.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * WebMvcConfig
 * Date: 04/10/2023
 *
 * @author Yauheni Antsipenka
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.epam.jmp.m11.rest.controller")
public class WebMvcConfig {

    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }
}
