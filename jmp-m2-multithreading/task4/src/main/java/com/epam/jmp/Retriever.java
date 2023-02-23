package com.epam.jmp;

import com.epam.jmp.domain.Employee;
import com.epam.jmp.exceptions.JMPConcurrentWorkException;
import com.epam.jmp.services.api.EmployeeController;
import com.epam.jmp.services.impl.FakeEmployeeControllerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Retriever
 * Date: 02/20/2023
 *
 * @author Yauheni Antsipenka
 */
public final class Retriever {

    private static final Logger LOGGER = LoggerFactory.getLogger(Retriever.class);

    private Retriever() {}

    public static void retrieveSalaryByEmployeeIdAsynchronously(int hiredEmployeeId) {
        EmployeeController controller = new FakeEmployeeControllerImpl();
        CompletableFuture<Integer> result = getEmployeesFuture(controller)
            .thenCompose(employee -> {
                try {
                    return getSalaryByEmployeeId(controller, getEmployeesFuture(controller).get(), hiredEmployeeId);
                } catch (InterruptedException | ExecutionException e) {
                    throw new JMPConcurrentWorkException(e);
                }
            });
        try {
            LOGGER.info("Result: {}", result.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new JMPConcurrentWorkException(e);
        }
    }

    private static CompletableFuture<List<Employee>> getEmployeesFuture(EmployeeController controller) {
        return CompletableFuture.supplyAsync(() -> {
            List<Employee> employees = controller.hiredEmployees();
            LOGGER.info("employees: {}", employees);
            return employees;
        });
    }

    private static CompletableFuture<Integer> getSalaryByEmployeeId(EmployeeController controller,
                                                                    List<Employee> employees, Integer hiredEmployeeId) {
        return CompletableFuture.supplyAsync(() -> {
            Integer salary = controller.getSalary(employees, hiredEmployeeId);
            LOGGER.info("Salary: {}", salary);
            return salary;
        });
    }
}
