package com.epam.jmp.jmpm7springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.epam.jmp.jmpm7springboot.domain.Role;
import com.epam.jmp.jmpm7springboot.domain.User;
import com.epam.jmp.jmpm7springboot.repository.RoleRepository;
import com.epam.jmp.jmpm7springboot.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import javax.transaction.Transactional;

/**
 * UserServiceTest
 * Date: 03/13/2023
 *
 * @author Yauheni Antsipenka
 */
@SpringBootTest
@Transactional
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    public void setUp() {
        Role userRole = new Role();
        userRole.setId(1);
        userRole.setName("USER");
        roleRepository.saveAll(List.of(userRole));
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setLogin("new_login");
        user.setPassword("new_password");
        userService.saveUser(user);
        assertEquals(user, userRepository.findByLogin("new_login"));
    }

    @Test
    public void testFindUserById() {
        User user = new User();
        user.setId(5);
        user.setLogin("new_login");
        user.setPassword("new_password");
        userService.saveUser(user);
        assertEquals(user, userRepository.findById(5).orElse(null));
    }

    @Test
    public void testFindUserByLogin() {
        User user = userService.findByLogin("user");
        assertEquals(user, userRepository.findById(user.getId()).orElse(null));
    }
}
