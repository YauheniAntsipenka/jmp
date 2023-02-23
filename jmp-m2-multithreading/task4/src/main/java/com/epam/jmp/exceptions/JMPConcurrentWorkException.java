package com.epam.jmp.exceptions;

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
