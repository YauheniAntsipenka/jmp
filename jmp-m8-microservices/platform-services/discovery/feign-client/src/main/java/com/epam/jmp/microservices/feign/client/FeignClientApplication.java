package com.epam.jmp.microservices.feign.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * FeignClientApplication
 * Date: 03/19/2023
 *
 * @author Yauheni Antsipenka
 */
@SpringBootApplication
@EnableEurekaClient
public class FeignClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignClientApplication.class, args);
    }
}
