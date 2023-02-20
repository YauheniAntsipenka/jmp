package task4.services.api;

import task4.domain.Employee;

import java.util.List;

/**
 * IInitializer
 * Date: 02/20/2023
 *
 * @author Yauheni Antsipenka
 */
public interface InitService {
    List<Employee> retrieveEmployees();
}
