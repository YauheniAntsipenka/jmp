package com.epam.jmp.spring.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.epam.jmp.spring.core.dao.impl.UserDaoImpl;
import com.epam.jmp.spring.core.exception.JMPDeleteException;
import com.epam.jmp.spring.core.exception.JMPSaveException;
import com.epam.jmp.spring.core.exception.JMPUpdateException;
import com.epam.jmp.spring.core.model.User;
import com.epam.jmp.spring.core.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * UserServiceImplTest
 * Date: 02/27/2023
 *
 * @author Yauheni Antsipenka
 */
public class UserServiceImplTest {

    public static final User FIRST_USER = new User(1, "name1", "email1");
    public static final User SECOND_USER = new User(2, "name2", "email2");
    public static final Map<Long, User> MAP_TO_INSERT = Map.of(1L, FIRST_USER, 2L, SECOND_USER);
    private static final long NOT_FOUND_USER_ID = 123;
    private final UserDaoImpl userDao = new UserDaoImpl();
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userDao.getUsersMap().putAll(MAP_TO_INSERT);
        userService = new UserServiceImpl(userDao);
    }

    @Test
    public void testGetUserById() {
        assertEquals(FIRST_USER, userService.findUserById(1));
    }

    @Test
    public void testGetUserByIdWhenNotFound() {
        assertNull(userService.findUserById(NOT_FOUND_USER_ID));
    }

    @Test
    public void testGetUserByEmail() {
        User user = userService.findUserByEmail("email2");
        assertEquals(SECOND_USER, user);
    }

    @Test
    public void testGetUserByEmailWhenNotFound() {
        assertNull(userService.findUserByEmail("email789"));
    }

    @Test
    public void testFindUsersByName() {
        List<User> users = userService.findUsersByName("name1", 0, 0);
        assertEquals(1, users.size());
        assertEquals(FIRST_USER, users.get(0));
    }

    @Test
    public void testFindUsersByNameWhenNotFound() {
        List<User> users = userService.findUsersByName("name789", 0, 0);
        assertEquals(0, users.size());
    }

    @Test
    public void testUpdate() {
        String newName = "title777";
        User user = new User(1, newName, "email1");
        userService.updateUser(user);
        assertEquals(newName, Objects.requireNonNull(userDao.get(1).orElse(null)).getName());
    }

    @Test
    public void testUpdateWhenNotFound() {
        User user = new User(NOT_FOUND_USER_ID, "newName", "email1");
        assertThrows(JMPUpdateException.class, () -> userService.updateUser(user));
    }

    @Test
    public void testDelete() {
        assertTrue(userService.deleteUser(1));
        assertNull(userDao.get(1).orElse(null));
    }

    @Test
    public void testDeleteWhenNotFound() {
        assertThrows(JMPDeleteException.class, () -> userService.deleteUser(NOT_FOUND_USER_ID));
    }

    @Test
    public void testCreate() {
        User newUser = new User(3, "name3", "email3");
        User savedUser = userService.createUser(newUser);
        assertEquals(savedUser, userDao.get(3).orElse(null));
    }

    @Test
    public void testCreateWhenAlreadyExists() {
        User newUser = new User(1, "name123", "email123");
        assertThrows(JMPSaveException.class, () -> userService.createUser(newUser));
    }
}
