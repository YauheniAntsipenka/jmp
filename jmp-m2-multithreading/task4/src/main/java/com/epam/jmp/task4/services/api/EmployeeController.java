package com.epam.jmp.task4.services.api;

import com.epam.jmp.task4.domain.Employee;

import java.util.List;

/**
 * EmployeeController
 * Date: 02/23/2023
 *
 * @author Yauheni Antsipenka
 */
public interface EmployeeController {
    List<Employee> hiredEmployees();
    Integer getSalary(List<Employee> employees, Integer hiredEmployeeId);
}
