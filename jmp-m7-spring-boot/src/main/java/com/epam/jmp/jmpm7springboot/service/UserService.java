package com.epam.jmp.jmpm7springboot.service;

import com.epam.jmp.jmpm7springboot.domain.Role;
import com.epam.jmp.jmpm7springboot.domain.User;
import com.epam.jmp.jmpm7springboot.repository.RoleRepository;
import com.epam.jmp.jmpm7springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService
 * Date: 03/13/2023
 *
 * @author Yauheni Antsipenka
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public User saveUser(User user) {
        Role userRole = roleRepository.findByName("USER");
        user.setRole(userRole);
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
