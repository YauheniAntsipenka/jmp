package com.epam.jmp.spring.core.dao.storage.impl;

import com.epam.jmp.spring.core.dao.storage.Storage;
import com.epam.jmp.spring.core.exception.RetrieveDataException;
import com.epam.jmp.spring.core.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * UsersStorageImpl
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class UsersStorageImpl implements Storage<User> {

    private static final String PATH = "init/users.json";

    @Override
    public Map<Long, User> retrieveInitDataFromFile() {
        Map<Long, User> usersMap = new HashMap<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            Arrays.asList(objectMapper.readValue(new File(Objects.requireNonNull(getClass().getClassLoader().getResource(PATH)).toURI()), User[].class))
                .forEach(user -> usersMap.put(user.getId(), user));
        } catch (IOException | URISyntaxException e) {
            throw new RetrieveDataException(e);
        }
        return usersMap;
    }
}
