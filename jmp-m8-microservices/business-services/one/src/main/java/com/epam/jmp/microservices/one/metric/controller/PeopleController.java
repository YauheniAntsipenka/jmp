package com.epam.jmp.microservices.one.metric.controller;

import com.epam.jmp.microservices.one.metric.exception.TestException;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletResponse;

/**
 * PeopleController
 * Date: 03/19/2023
 *
 * @author Yauheni Antsipenka
 */
@RestController
@Timed("people")
public class PeopleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeopleController.class);
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    @GetMapping("/people")
    @Timed(value = "people.all", longTask = true)
    public List<String> listPeople() throws InterruptedException {
        int seconds2Sleep = SECURE_RANDOM.nextInt(1000);
        LOGGER.info("Seconds to sleep: {}", seconds2Sleep);
        TimeUnit.MILLISECONDS.sleep(seconds2Sleep);
        return Arrays.asList("Jim", "Tom", "Tim");
    }

    @PostMapping("/people")
    @Timed(value = "people.update", longTask = true)
    public List<String> putPeople() throws InterruptedException {
        int seconds2Sleep = SECURE_RANDOM.nextInt(1000);
        LOGGER.info("Seconds to sleep: {}", seconds2Sleep);
        TimeUnit.MILLISECONDS.sleep(seconds2Sleep);
        return Arrays.asList("Jim", "Tom", "Tim");
    }

    @GetMapping("/asset")
    @Timed(value = "people.asset", longTask = true)
    public void test() throws TestException {
        throw new TestException("error!");
    }

    @GetMapping("/property")
    @Timed(value = "people.property", longTask = true)
    public void property(HttpServletResponse response) throws IOException {
        response.sendRedirect("/asset");
    }

}
