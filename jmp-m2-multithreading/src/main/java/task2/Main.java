package task2;

import task2.services.api.InitService;
import task2.services.impl.InitServiceImpl;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

/**
 * Main
 * Date: 02/04/2023
 *
 * @author Yauheni Antsipenka
 */
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        InitService initService = new InitServiceImpl();
        Runner.runQuickSortDefault(
            Arrays.copyOf(initService.initializeArray(), initService.initializeArray().length));
        Runner.runQuickSortAsynchronously(
            Arrays.copyOf(initService.initializeArray(), initService.initializeArray().length));
    }
}
