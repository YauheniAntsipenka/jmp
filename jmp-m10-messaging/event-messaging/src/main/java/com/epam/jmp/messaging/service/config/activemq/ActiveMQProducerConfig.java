package com.epam.jmp.messaging.service.config.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

/**
 * ActiveMQProducerConfig
 * Date: 04/03/2023
 *
 * @author Yauheni Antsipenka
 */
@Configuration
@Profile("activemq")
public class ActiveMQProducerConfig {

    @Value(value = "${create.notification.queue.name}")
    private String createNotificationQueueName;
    @Value(value = "${update.notification.queue.name}")
    private String updateNotificationQueueName;
    @Value(value = "${delete.notification.queue.name}")
    private String deleteNotificationQueueName;
    @Value(value = "${create.request.queue.name}")
    private String createRequestQueueName;
    @Value(value = "${update.request.queue.name}")
    private String updateRequestQueueName;
    @Value(value = "${delete.request.queue.name}")
    private String deleteRequestQueueName;

    @Value("${active-mq.broker-url}")
    private String brokerUrl;

    @Bean
    public ConnectionFactory connectionProducerFactory(){
        ActiveMQConnectionFactory activeMQConnectionFactory  = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerUrl);
        activeMQConnectionFactory.setTrustAllPackages(true);
        return  activeMQConnectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionProducerFactory());
        jmsTemplate.setPubSubDomain(true);
        return jmsTemplate;
    }

    @Bean
    public ActiveMQQueue createNotificationQueue() {
        return new ActiveMQQueue(createNotificationQueueName);
    }

    @Bean
    public ActiveMQQueue updateNotificationQueue() {
        return new ActiveMQQueue(updateNotificationQueueName);
    }

    @Bean
    public ActiveMQQueue deleteNotificationQueue() {
        return new ActiveMQQueue(deleteNotificationQueueName);
    }

    @Bean
    public ActiveMQQueue createRequestQueue() {
        return new ActiveMQQueue(createRequestQueueName);
    }

    @Bean
    public ActiveMQQueue updateRequestQueue() {
        return new ActiveMQQueue(updateRequestQueueName);
    }

    @Bean
    public ActiveMQQueue deleteRequestQueue() {
        return new ActiveMQQueue(deleteRequestQueueName);
    }
}
