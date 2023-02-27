package com.epam.jmp.spring.core.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.epam.jmp.spring.core.model.User;
import com.epam.jmp.spring.core.model.impl.UserImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Objects;

/**
 * UserDaoTest
 * Date: 02/27/2023
 *
 * @author Yauheni Antsipenka
 */
public class UserDaoTest {

    public static final User FIRST_USER = new UserImpl(1, "name1", "email1");
    public static final User SECOND_USER = new UserImpl(2, "name2", "email2");
    public static final Map<Long, User> MAP_TO_INSERT = Map.of(1L, FIRST_USER, 2L, SECOND_USER);
    private UserDao userDao;

    @BeforeEach
    public void setUp() {
        userDao = new UserDao();
        userDao.getUsersMap().putAll(MAP_TO_INSERT);
    }

    @Test
    public void testGetById() {
        User user = userDao.get(2).orElse(null);
        assertNotNull(user);
        assertEquals(SECOND_USER, user);
    }

    @Test
    public void testGetAll() {
        assertEquals(2, userDao.getAll().size());
        assertEquals(userDao.get(1).orElse(null), MAP_TO_INSERT.get(1L));
        assertEquals(userDao.get(2).orElse(null), MAP_TO_INSERT.get(2L));
    }

    @Test
    public void testUpdate() {
        String newName = "name777";
        User user = new UserImpl(2, newName, "email1");
        userDao.update(user);
        assertEquals(newName, Objects.requireNonNull(userDao.get(2).orElse(null)).getName());
    }

    @Test
    public void testDelete() {
        userDao.delete(1);
        assertNull(userDao.get(1).orElse(null));
    }

    @Test
    public void testSave() {
        User newUser = new UserImpl(3, "name3", "email3");
        userDao.save(newUser);
        assertEquals(newUser, userDao.get(3).orElse(null));
    }
}
