package com.epam.jmp.task4.services.impl;

import com.epam.jmp.task4.domain.Employee;
import com.epam.jmp.task4.services.api.EmployeeController;

import java.util.List;
import java.util.Objects;

/**
 * FakeEmployeeControllerImpl
 * Date: 02/23/2023
 *
 * @author Yauheni Antsipenka
 */
public class FakeEmployeeControllerImpl implements EmployeeController {

    @Override
    public List<Employee> hiredEmployees() {
        return new JMPInitializerImpl().retrieveEmployees();
    }

    @Override
    public Integer getSalary(List<Employee> employees, Integer hiredEmployeeId) {
        return employees.stream()
            .filter(employee -> Objects.equals(employee.getId(), hiredEmployeeId))
            .findFirst().map(Employee::getSalary)
            .orElse(null);
    }
}
