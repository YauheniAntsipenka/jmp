package com.epam.jmp.task3;

import com.epam.jmp.task3.services.api.JMPInitializer;
import com.epam.jmp.task3.services.impl.JMPInitializerImpl;

import java.util.concurrent.ExecutionException;

/**
 * Main
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        JMPInitializer initializer = new JMPInitializerImpl();
        FilesChecker.checkNumberOfFilesAndFoldersByPathAsynchronously(
            JMPInitializerImpl.PATH_TO_SCAN, initializer.retrieveMapToCollectResults());
    }
}
