package com.epam.jmp.microservices.one.metric.controller;

import com.epam.jmp.microservices.one.metric.exception.TestException;
import com.netflix.servo.annotations.DataSourceLevel;
import com.netflix.servo.annotations.DataSourceType;
import com.netflix.servo.annotations.Monitor;
import com.netflix.servo.annotations.MonitorTags;
import com.netflix.servo.monitor.Monitors;
import com.netflix.servo.tag.BasicTagList;
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
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;
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

    @Monitor(name = "requestCounter", type = DataSourceType.COUNTER, description = "Total number of requests",
        level = DataSourceLevel.INFO)
    private final AtomicInteger requestCounter = new AtomicInteger(0);

    @Monitor(name = "aGauge", type = DataSourceType.GAUGE, description = "A random gauge",
        level = DataSourceLevel.CRITICAL)
    private final AtomicInteger aGauge = new AtomicInteger(0);

    @MonitorTags
    private final BasicTagList tags = BasicTagList.of("id", "peopleController", "class",
        "com.epam.jmp.microservices.one.metric.controller.PeopleController");

    @PostConstruct
    public void init() {
        Monitors.registerObject("peopleController", this);
    }

    @GetMapping("/people")
    @Timed(value = "people.all", longTask = true)
    public List<String> listPeople() throws InterruptedException {
        int seconds2Sleep = SECURE_RANDOM.nextInt(1000);
        LOGGER.info("Seconds to sleep: {}", seconds2Sleep);
        TimeUnit.MILLISECONDS.sleep(seconds2Sleep);
        requestCounter.incrementAndGet();
        aGauge.set(SECURE_RANDOM.nextInt(100));
        return Arrays.asList("Jim", "Tom", "Tim");
    }

    @PostMapping("/people")
    @Timed(value = "people.update", longTask = true)
    public List<String> putPeople() throws InterruptedException {
        int seconds2Sleep = SECURE_RANDOM.nextInt(1000);
        LOGGER.info("Seconds to sleep: {}", seconds2Sleep);
        requestCounter.incrementAndGet();
        aGauge.set(SECURE_RANDOM.nextInt(100));
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
