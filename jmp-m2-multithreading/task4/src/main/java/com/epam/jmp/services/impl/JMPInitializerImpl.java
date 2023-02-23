package com.epam.jmp.services.impl;

import com.epam.jmp.domain.Employee;
import com.epam.jmp.services.api.JMPInitializer;

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
