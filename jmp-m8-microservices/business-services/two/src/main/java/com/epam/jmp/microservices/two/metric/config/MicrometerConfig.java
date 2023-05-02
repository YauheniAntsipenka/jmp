package com.epam.jmp.microservices.two.metric.config;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * MicrometerConfig
 * Date: 03/19/2023
 *
 * @author Yauheni Antsipenka
 */
@Configuration
@EnableAspectJAutoProxy
public class MicrometerConfig {

    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }
}
