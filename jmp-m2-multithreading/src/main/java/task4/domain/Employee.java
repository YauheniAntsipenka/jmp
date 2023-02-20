package task4.domain;

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
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("id=").append(id);
        sb.append(", salary=").append(salary);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
