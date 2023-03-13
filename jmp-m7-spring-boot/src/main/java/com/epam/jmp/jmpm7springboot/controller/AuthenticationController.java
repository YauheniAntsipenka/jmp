package com.epam.jmp.jmpm7springboot.controller;

import com.epam.jmp.jmpm7springboot.config.jwt.JwtProvider;
import com.epam.jmp.jmpm7springboot.domain.JwtRequest;
import com.epam.jmp.jmpm7springboot.domain.JwtResponse;
import com.epam.jmp.jmpm7springboot.domain.RegistrationRequest;
import com.epam.jmp.jmpm7springboot.domain.User;
import com.epam.jmp.jmpm7springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthenticationController
 * Date: 03/13/2023
 *
 * @author Yauheni Antsipenka
 */
@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public String registerUser(@RequestBody RegistrationRequest registrationRequest) {
        User u = new User();
        u.setPassword(registrationRequest.getPassword());
        u.setLogin(registrationRequest.getLogin());
        userService.saveUser(u);
        return "OK";
    }

    @PostMapping("/auth")
    public JwtResponse auth(@RequestBody JwtRequest request) {
        User user = userService.findByLogin(request.getUsername());
        String token = jwtProvider.generateToken(user.getLogin());
        return new JwtResponse(token);
    }
}
