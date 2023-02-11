package task4;

import task4.domain.Employee;

import java.util.ArrayList;

/**
 * Initializer
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class Initializer {
    public static final ArrayList<Employee> EMPLOYEES = new ArrayList<>() {{
        add(new Employee(1, 1000, "Petr"));
        add(new Employee(2, 300, "Ivan"));
        add(new Employee(3, 1500, "Valera"));
    }};
}
