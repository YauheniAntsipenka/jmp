package com.epam.jmp;

import com.epam.jmp.services.api.JMPInitializer;
import com.epam.jmp.services.impl.JMPInitializerImpl;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

/**
 * Main
 * Date: 02/04/2023
 *
 * @author Yauheni Antsipenka
 */
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        JMPInitializer initializer = new JMPInitializerImpl();
        QuickSortRunner.run(Arrays.copyOf(
            initializer.initializeArray(), initializer.initializeArray().length));
        QuickSortRunner.runAsynchronously(
            Arrays.copyOf(initializer.initializeArray(), initializer.initializeArray().length));
    }
}
