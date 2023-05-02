package com.epam.jmp.microservices.eureka.client.controller;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * GreetingController
 * Date: 03/19/2023
 *
 * @author Yauheni Antsipenka
 */
@RestController
public class GreetingController {

    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    @RequestMapping("/greeting")
    public String greeting() {
        return String.format(
            "Hello from '%s'!", eurekaClient.getApplication(appName).getName());
    }
}
