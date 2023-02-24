package com.epam.jmp.task3.services.api;

import com.epam.jmp.task3.domain.CheckerEnum;

import java.util.Map;

/**
 * JMPInitializer
 * Date: 02/20/2023
 *
 * @author Yauheni Antsipenka
 */
public interface JMPInitializer {
    Map<CheckerEnum, Integer> retrieveMapToCollectResults();
}
