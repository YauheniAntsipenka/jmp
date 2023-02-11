package task4;

import task4.domain.Employee;

import java.util.List;
import java.util.Objects;

/**
 * FakeRest
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class FakeRest {

    public static List<Employee> hiredEmployees() {
        return Initializer.EMPLOYEES;
    }

    public static Integer getSalary(List<Employee> employees, Integer hiredEmployeeId) {
        return employees.stream()
            .filter(employee -> Objects.equals(employee.getId(), hiredEmployeeId))
            .findFirst().map(Employee::getSalary)
            .orElse(null);
    }
}
