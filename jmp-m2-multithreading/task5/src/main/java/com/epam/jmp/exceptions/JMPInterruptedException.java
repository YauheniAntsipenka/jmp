package com.epam.jmp.exceptions;

/**
 * JMPInterruptedException
 * Date: 02/23/2023
 *
 * @author Yauheni Antsipenka
 */
public class JMPInterruptedException extends RuntimeException {
    public JMPInterruptedException(Exception e) {
        super(e);
    }
}
