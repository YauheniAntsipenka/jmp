package com.epam.jmp.spring.core.exception;

/**
 * RetrieveDataException
 * Date: 03/21/2023
 *
 * @author Yauheni Antsipenka
 */
public class RetrieveDataException extends RuntimeException {
    public RetrieveDataException(Exception e) {
        super(e);
    }
}
