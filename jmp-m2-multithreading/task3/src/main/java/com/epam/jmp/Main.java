package com.epam.jmp;

import com.epam.jmp.services.api.JMPInitializer;
import com.epam.jmp.services.impl.JMPInitializerImpl;

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
