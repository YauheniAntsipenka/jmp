package task4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task4.domain.Employee;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Runner
 * Date: 02/20/2023
 *
 * @author Yauheni Antsipenka
 */
public final class Runner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);

    private Runner() {}

    public static void retrieveSalaryByEmployeeIdAsynchronously(int hiredEmployeeId) throws ExecutionException, InterruptedException {
        FakeRest fakeRest = new FakeRest();
        CompletableFuture<Integer> result = getEmployeesFuture(fakeRest)
            .thenCompose(employee -> {
                try {
                    return getSalaryByEmployeeId(fakeRest, getEmployeesFuture(fakeRest).get(), hiredEmployeeId);
                } catch (InterruptedException | ExecutionException e) {
                    throw new JMPModule2Task4Exception(e);
                }
            });
        LOGGER.info(String.valueOf(result.get()));
    }

    private static CompletableFuture<List<Employee>> getEmployeesFuture(FakeRest fakeRest) {
        return CompletableFuture.supplyAsync(() -> {
            List<Employee> employees = fakeRest.hiredEmployees();
            LOGGER.info(String.valueOf(employees));
            return employees;
        });
    }

    private static CompletableFuture<Integer> getSalaryByEmployeeId(FakeRest fakeRest, List<Employee> employees,
                                                                    Integer hiredEmployeeId) {
        return CompletableFuture.supplyAsync(() -> {
            Integer salary = fakeRest.getSalary(employees, hiredEmployeeId);
            LOGGER.info("Salary: {}", salary);
            return salary;
        });
    }
}

class JMPModule2Task4Exception extends RuntimeException {
    public JMPModule2Task4Exception(Exception e) {
        super(e);
    }
}
