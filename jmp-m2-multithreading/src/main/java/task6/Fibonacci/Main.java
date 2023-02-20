package task6.Fibonacci;

import java.util.concurrent.ExecutionException;

/**
 * Main
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int number = 45;
        Runner.calculateFibonacci(number);
        Runner.calculateFibonacciAsynchronously(number);
    }
}
