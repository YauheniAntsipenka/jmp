package com.epam.jmp.exceptions;

/**
 * JMPIOException
 * Date: 02/23/2023
 *
 * @author Yauheni Antsipenka
 */
public class JMPIOException extends RuntimeException {
    public JMPIOException(Exception e) {
        super(e);
    }
}
