package task3;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Initializer
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class Initializer {
    public static final String path = "C://apache-maven-3.8.7";
    public static final Map<CheckerEnum, Integer> map = new ConcurrentHashMap<>(){{
        put(CheckerEnum.FILES_COUNT, 0);
        put(CheckerEnum.FILES_SIZE, 0);
        put(CheckerEnum.FOLDERS_COUNT, 0);
    }};
}
