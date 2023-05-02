package com.epam.jmp.messaging.service.config.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

/**
 * ActiveMQConsumerConfig
 * Date: 04/03/2023
 *
 * @author Yauheni Antsipenka
 */
@Configuration
@Profile("activemq")
public class ActiveMQConsumerConfig {

    @Value("${active-mq.broker-url}")
    private String brokerUrl;

    @Bean
    public ConnectionFactory connectionConsumerFactory(){
        ActiveMQConnectionFactory activeMQConnectionFactory  = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerUrl);
        activeMQConnectionFactory.setTrustAllPackages(true);
        return activeMQConnectionFactory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionConsumerFactory());
        factory.setPubSubDomain(true);
        return factory;
    }
}
