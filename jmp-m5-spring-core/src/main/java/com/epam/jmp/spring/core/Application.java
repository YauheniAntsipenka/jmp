package com.epam.jmp.spring.core;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ResourceLoader;

/**
 * SpringRunner
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class Application {

    private static final String APPLICATION_CONTEXT_PATH = "/ApplicationContext.xml";

    public static void main(String[] args) {
        JMPRunner.runApp(new ClassPathXmlApplicationContext(ResourceLoader.CLASSPATH_URL_PREFIX + APPLICATION_CONTEXT_PATH));
    }
}
