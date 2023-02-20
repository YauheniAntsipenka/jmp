package task4.services.impl;

import task4.domain.Employee;
import task4.services.api.InitService;

import java.util.List;

/**
 * Initializer
 * Date: 02/04/2023
 *
 * @author Yauheni Antsipenka
 */
public class InitServiceImpl implements InitService {

    @Override
    public List<Employee> retrieveEmployees() {
        return List.of(new Employee(1, 1000, "Petr"), new Employee(2, 300, "Ivan"), new Employee(3, 1500, "Valera"));
    }
}
