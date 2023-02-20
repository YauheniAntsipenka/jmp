package task3.services.impl;

import task3.domain.CheckerEnum;
import task3.services.api.InitService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Initializer
 * Date: 02/04/2023
 *
 * @author Yauheni Antsipenka
 */
public class InitServiceImpl implements InitService {

    private static final String PATH_TO_SCAN = "C://apache-maven-3.8.7";

    @Override
    public String retrievePathToScan() {
        return PATH_TO_SCAN;
    }

    @Override
    public Map<CheckerEnum, Integer> retrieveMapToCollectResults() {
        return new ConcurrentHashMap<>(){{
            put(CheckerEnum.FILES_COUNT, 0);
            put(CheckerEnum.FILES_SIZE, 0);
            put(CheckerEnum.FOLDERS_COUNT, 0);
        }};
    }
}
