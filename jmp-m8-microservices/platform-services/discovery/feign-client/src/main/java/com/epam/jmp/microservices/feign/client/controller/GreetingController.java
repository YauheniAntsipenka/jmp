package com.epam.jmp.microservices.feign.client.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * GreetingController
 * Date: 03/19/2023
 *
 * @author Yauheni Antsipenka
 */
@Controller
public class GreetingController {

    @Autowired
    private EurekaClient eurekaClient;

    @RequestMapping("/get-greeting")
    public String greeting(Model model) {

        InstanceInfo service = eurekaClient
            .getApplication("spring-cloud-eureka-client")
            .getInstances()
            .get(0);

        String hostName = service.getHostName();
        int port = service.getPort();

        model.addAttribute("greeting", hostName + " " + port);
        return "greeting-view";
    }
}
