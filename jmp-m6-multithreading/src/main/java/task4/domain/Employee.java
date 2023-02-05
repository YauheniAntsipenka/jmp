package task4.domain;

import java.util.StringJoiner;

/**
 * Employee
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class Employee {
    private final Integer id;
    private final Integer salary;
    private final String name;

    public Employee(Integer id, Integer salary, String name) {
        this.id = id;
        this.salary = salary;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public Integer getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Employee.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("salary=" + salary)
            .add("name='" + name + "'")
            .toString();
    }
}
