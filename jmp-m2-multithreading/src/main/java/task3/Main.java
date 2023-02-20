package task3;

import task3.services.api.InitService;
import task3.services.impl.InitServiceImpl;

import java.util.concurrent.ExecutionException;

/**
 * Main
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        InitService initService = new InitServiceImpl();
        Runner.checkNumberOfFilesAndFoldersByPathAsynchronously(
            initService.retrievePathToScan(), initService.retrieveMapToCollectResults());
    }
}
