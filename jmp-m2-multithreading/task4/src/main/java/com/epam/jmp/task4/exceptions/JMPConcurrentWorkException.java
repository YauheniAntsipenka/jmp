package com.epam.jmp.task4.exceptions;

/**
 * JMPConcurrentWorkException
 * Date: 02/23/2023
 *
 * @author Yauheni Antsipenka
 */
public class JMPConcurrentWorkException extends RuntimeException {
    public JMPConcurrentWorkException(Exception e) {
        super(e);
    }
}
