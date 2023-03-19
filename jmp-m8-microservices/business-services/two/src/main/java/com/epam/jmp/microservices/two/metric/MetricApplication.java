package com.epam.jmp.microservices.two.metric;

import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * MetricApplication
 * Date: 03/19/2023
 *
 * @author Yauheni Antsipenka
 */
@SpringBootApplication
public class MetricApplication {

    @Bean
    public JvmThreadMetrics threadMetrics() {
        return new JvmThreadMetrics();
    }

    public static void main(String[] args) {
        SpringApplication.run(MetricApplication.class);
    }
}
