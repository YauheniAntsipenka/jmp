package task3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * Main
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(3);
        long startTime = System.nanoTime();
        ForkJoinTask<Map<CheckerEnum, Integer>> checker =
            forkJoinPool.submit(new CheckerRecursiveTask(Initializer.path, Initializer.map));
        Map<CheckerEnum, Integer> counts = checker.get();
        long endTime = System.nanoTime();
        LOGGER.info("fileCount: {}, fileSizes: {}, folderCount: {}", counts.get(CheckerEnum.FILES_COUNT),
            counts.get(CheckerEnum.FILES_SIZE), counts.get(CheckerEnum.FOLDERS_COUNT));
        LOGGER.info("Time to execute: {}", endTime - startTime);
    }
}
