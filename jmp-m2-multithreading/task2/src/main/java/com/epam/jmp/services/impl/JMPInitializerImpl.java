package com.epam.jmp.services.impl;

import com.epam.jmp.services.api.JMPInitializer;

/**
 * JMPInitializerImpl
 * Date: 02/04/2023
 *
 * @author Yauheni Antsipenka
 */
public class JMPInitializerImpl implements JMPInitializer {

    @Override
    public int[] initializeArray() {
        return new int[]{5, 9, 4, 6, 5, 3, -1, 3, 5, -45, 45, 2};
    }
}
