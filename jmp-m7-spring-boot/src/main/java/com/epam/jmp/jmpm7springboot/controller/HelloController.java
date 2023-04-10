package com.epam.jmp.jmpm7springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController
 * Date: 03/13/2023
 *
 * @author Yauheni Antsipenka
 */
@RestController
@RequestMapping("api/v1/")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
