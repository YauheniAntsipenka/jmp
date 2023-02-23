package com.epam.jmp.services.api;

import com.epam.jmp.domain.CheckerEnum;

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
