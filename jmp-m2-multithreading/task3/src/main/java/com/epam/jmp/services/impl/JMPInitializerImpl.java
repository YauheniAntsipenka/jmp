package com.epam.jmp.services.impl;

import com.epam.jmp.domain.CheckerEnum;
import com.epam.jmp.services.api.JMPInitializer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * JMPInitializerImpl
 * Date: 02/04/2023
 *
 * @author Yauheni Antsipenka
 */
public class JMPInitializerImpl implements JMPInitializer {

    public static final String PATH_TO_SCAN = "C://apache-maven-3.8.7";

    @Override
    public Map<CheckerEnum, Integer> retrieveMapToCollectResults() {
        return new ConcurrentHashMap<>(){{
            put(CheckerEnum.FILES_COUNT, 0);
            put(CheckerEnum.FILES_SIZE, 0);
            put(CheckerEnum.FOLDERS_COUNT, 0);
        }};
    }
}
