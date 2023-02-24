package com.epam.jmp.task4.services.impl;

import com.epam.jmp.task4.domain.Employee;
import com.epam.jmp.task4.services.api.JMPInitializer;

import java.util.List;

/**
 * JMPInitializerImpl
 * Date: 02/04/2023
 *
 * @author Yauheni Antsipenka
 */
public class JMPInitializerImpl implements JMPInitializer {

    @Override
    public List<Employee> retrieveEmployees() {
        return List.of(new Employee(1, 1000, "Petr"), new Employee(2, 300, "Ivan"), new Employee(3, 1500, "Valera"));
    }
}
