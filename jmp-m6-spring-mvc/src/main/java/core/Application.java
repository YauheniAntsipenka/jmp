package core;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ResourceLoader;

/**
 * SpringRunner
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class Application {

    public static void main(String[] args) {
        JMPRunner.runApp(new ClassPathXmlApplicationContext(ResourceLoader.CLASSPATH_URL_PREFIX + "/context/ApplicationContext.xml"));
    }
}
