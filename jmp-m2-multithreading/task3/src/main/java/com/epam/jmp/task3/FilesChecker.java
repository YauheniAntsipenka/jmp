package com.epam.jmp.task3;

import com.epam.jmp.task3.domain.CheckerEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * FilesChecker
 * Date: 02/20/2023
 *
 * @author Yauheni Antsipenka
 */
public final class FilesChecker {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilesChecker.class);

    private FilesChecker(){}

    public static void checkNumberOfFilesAndFoldersByPathAsynchronously(String path,
                                                                        Map<CheckerEnum, Integer> mapToCollectResults)
        throws ExecutionException, InterruptedException {
        long startTime = System.nanoTime();
        Map<CheckerEnum, Integer> counts =
            new ForkJoinPool(3).submit(new CheckerRecursiveTask(path, mapToCollectResults)).get();
        long endTime = System.nanoTime();
        LOGGER.info("fileCount: {}, fileSizes: {}, folderCount: {}", counts.get(CheckerEnum.FILES_COUNT),
            counts.get(CheckerEnum.FILES_SIZE), counts.get(CheckerEnum.FOLDERS_COUNT));
        LOGGER.info("Time to execute: {}", endTime - startTime);
    }
}
