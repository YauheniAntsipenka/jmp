package com.epam.jmp.task3;

import com.epam.jmp.task3.domain.CheckerEnum;
import com.epam.jmp.task3.exceptions.JMPIOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

/**
 * CheckerRecursiveTask
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class CheckerRecursiveTask extends RecursiveTask<Map<CheckerEnum, Integer>> {

    public static final String SEPARATOR = "/";
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckerRecursiveTask.class);

    public final String path;
    private final Map<CheckerEnum, Integer> map;

    public CheckerRecursiveTask(String path, Map<CheckerEnum, Integer> map) {
        this.path = path;
        this.map = map;
    }

    @Override
    protected Map<CheckerEnum, Integer> compute() {
        File currentDir = new File(path);
        List<File> files = getFiles(currentDir);
        map.put(CheckerEnum.FILES_COUNT, map.get(CheckerEnum.FILES_COUNT) + files.size());
        map.put(CheckerEnum.FILES_SIZE, (int) (map.get(CheckerEnum.FILES_SIZE) + getFileSizes(files).getSum()));
        List<File> folders = getFolders(currentDir);
        map.put(CheckerEnum.FOLDERS_COUNT, map.get(CheckerEnum.FOLDERS_COUNT) + folders.size());
        folders.forEach(folder -> {
            CheckerRecursiveTask checker =
                new CheckerRecursiveTask(path + SEPARATOR + folder.getName(), map);
            checker.fork().join();
        });
        LOGGER.info("Calculating..");
        return map;
    }

    private LongSummaryStatistics getFileSizes(List<File> files) {
        return files.stream().map(file -> {
            try {
                return Files.size(Paths.get(file.getAbsolutePath()));
            } catch (IOException e) {
                throw new JMPIOException(e);
            }
        }).collect(Collectors.summarizingLong(Long::longValue));
    }

    private List<File> getFiles(File currentDir) {
        return Arrays.stream(Objects.requireNonNull(currentDir.listFiles()))
            .filter(File::isFile)
            .collect(Collectors.toList());
    }

    private List<File> getFolders(File currentDir) {
        return Arrays.stream(Objects.requireNonNull(currentDir.listFiles()))
            .filter(File::isDirectory)
            .collect(Collectors.toList());
    }
}
