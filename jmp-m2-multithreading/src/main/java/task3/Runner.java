package task3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task3.domain.CheckerEnum;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * Runner
 * Date: 02/20/2023
 *
 * @author Yauheni Antsipenka
 */
public final class Runner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);

    private Runner(){}

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
