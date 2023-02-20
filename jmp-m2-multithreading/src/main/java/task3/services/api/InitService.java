package task3.services.api;

import task3.domain.CheckerEnum;

import java.util.Map;

/**
 * IInitializer
 * Date: 02/20/2023
 *
 * @author Yauheni Antsipenka
 */
public interface InitService {
    String retrievePathToScan();
    Map<CheckerEnum, Integer> retrieveMapToCollectResults();
}
