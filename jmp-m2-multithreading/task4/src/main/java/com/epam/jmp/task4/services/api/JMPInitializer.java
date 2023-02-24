package com.epam.jmp.task4.services.api;

import com.epam.jmp.task4.domain.Employee;

import java.util.List;

/**
 * JMPInitializer
 * Date: 02/20/2023
 *
 * @author Yauheni Antsipenka
 */
public interface JMPInitializer {
    List<Employee> retrieveEmployees();
}
