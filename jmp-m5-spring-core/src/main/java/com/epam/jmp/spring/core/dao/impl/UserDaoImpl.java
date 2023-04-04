package com.epam.jmp.spring.core.dao.impl;

import com.epam.jmp.spring.core.dao.UserDao;
import com.epam.jmp.spring.core.dao.storage.Storage;
import com.epam.jmp.spring.core.exception.JMPDeleteException;
import com.epam.jmp.spring.core.exception.JMPSaveException;
import com.epam.jmp.spring.core.exception.JMPUpdateException;
import com.epam.jmp.spring.core.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * UserDao
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    private final Map<Long, User> usersMap = new HashMap<>();
    private Storage<User> storage;

    @Override
    public Optional<User> get(long id) {
        return Optional.ofNullable(usersMap.get(id));
    }

    @Override
    public Collection<User> getAll() {
        return usersMap.values();
    }

    @Override
    public User save(User user) {
        if (usersMap.keySet().stream().anyMatch(id -> id == user.getId())) {
            LOGGER.info("User with id {} exists in map", user.getId());
            throw new JMPSaveException(Save exception);
        }

        usersMap.put(user.getId(), user);
        LOGGER.info("User with id {} was created", user.getId());
        return user;
    }

    @Override
    public User update(User user) {
        if (usersMap.keySet().stream().noneMatch(id -> id == user.getId())) {
            LOGGER.info("User with id {} not exists in map", user.getId());
            throw new JMPUpdateException("Update exception");
        }

        User userToUpdate = usersMap.get(user.getId());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setName(user.getName());
        usersMap.put(user.getId(), userToUpdate);
        LOGGER.info("User with id {} was updated", user.getId());
        return user;
    }

    @Override
    public boolean delete(long userId) {
        if (usersMap.keySet().stream().noneMatch(id -> id == userId)) {
            LOGGER.info("User with id {} not exists in map", userId);
            throw new JMPDeleteException("Delete exception");
        }

        usersMap.remove(userId);
        LOGGER.info("User with id {} was removed", userId);
        return true;
    }

    @Override
    public Map<Long, User> getUsersMap() {
        return usersMap;
    }

    public void setStorage(Storage<User> storage) {
        this.storage = storage;
    }

    private void init() {
        usersMap.putAll(storage.retrieveInitDataFromFile());
    }
}
