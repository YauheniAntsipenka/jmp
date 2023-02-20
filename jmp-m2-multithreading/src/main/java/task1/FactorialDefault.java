package task1;

import java.math.BigInteger;

/**
 * FactorialDefault
 * Date: 02/04/2023
 *
 * @author Yauheni Antsipenka
 */
public final class FactorialDefault {

    public static BigInteger get (int number) {
        BigInteger fact = BigInteger.valueOf(1);

        for(int i = 1; i <= number; i++){
            fact = fact.multiply(BigInteger.valueOf(i));
        }
        
        return fact;
    }
}
