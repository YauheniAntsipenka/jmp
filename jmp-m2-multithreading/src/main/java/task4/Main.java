package task4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task4.domain.Employee;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Main
 * Date: 02/05/2023
 *
 * @author Yauheni Antsipenka
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    public static final int EMPLOYEE_ID_TO_GET_SALARY = 2;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> result = getEmployeesFuture()
            .thenCompose(employee -> {
                try {
                    return getSalaryByEmployeeId(getEmployeesFuture().get(), EMPLOYEE_ID_TO_GET_SALARY);
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });
        LOGGER.info(String.valueOf(result.get()));
    }

    private static CompletableFuture<List<Employee>> getEmployeesFuture() {
        return CompletableFuture.supplyAsync(() -> {
            List<Employee> employees = FakeRest.hiredEmployees();
            LOGGER.info(String.valueOf(employees));
            return employees;
        });
    }

    private static CompletableFuture<Integer> getSalaryByEmployeeId(List<Employee> employees, Integer hiredEmployeeId) {
        return CompletableFuture.supplyAsync(() -> {
            Integer salary = FakeRest.getSalary(employees, hiredEmployeeId);
            LOGGER.info("Salary: {}", salary);
            return salary;
        });
    }
}
