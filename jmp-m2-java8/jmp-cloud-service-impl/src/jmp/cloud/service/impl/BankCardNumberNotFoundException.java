package jmp.cloud.service.impl;

/**
 * BankCardNumberNotFoundException
 * Date: 01/27/2023
 *
 * @author Yauheni Antsipenka
 */
public class BankCardNumberNotFoundException extends RuntimeException {

    public BankCardNumberNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
