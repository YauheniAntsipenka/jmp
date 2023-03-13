package com.epam.jmp.jmpm7springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestSecurityController
 * Date: 03/13/2023
 *
 * @author Yauheni Antsipenka
 */
@RestController
@RequestMapping("/api/v1")
public class TestSecurityController {

    @GetMapping("/admin/get")
    public String getAdmin() {
        return "Hi admin";
    }

    @GetMapping("/user/get")
    public String getUser() {
        return "Hi user";
    }
}
