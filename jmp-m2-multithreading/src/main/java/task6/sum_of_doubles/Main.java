package task6.sum_of_doubles;

import task6.sum_of_doubles.services.impl.InitServiceImpl;

/**
 * Main
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class Main {

    public static void main(String[] args) {
        double[] array = new InitServiceImpl().retrieveArray();
        Runner.calculateSumOfSquares(array);
        Runner.calculateSumOfSquaresAsynchronously(array);
    }
}
