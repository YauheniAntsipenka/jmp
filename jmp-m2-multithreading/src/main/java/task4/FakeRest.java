package task4;

import task4.domain.Employee;
import task4.services.impl.InitServiceImpl;

import java.util.List;
import java.util.Objects;

/**
 * FakeRest
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class FakeRest {

    public List<Employee> hiredEmployees() {
        return new InitServiceImpl().retrieveEmployees();
    }

    public Integer getSalary(List<Employee> employees, Integer hiredEmployeeId) {
        return employees.stream()
            .filter(employee -> Objects.equals(employee.getId(), hiredEmployeeId))
            .findFirst().map(Employee::getSalary)
            .orElse(null);
    }
}
