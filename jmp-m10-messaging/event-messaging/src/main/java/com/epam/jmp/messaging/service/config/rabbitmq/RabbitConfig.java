package com.epam.jmp.messaging.service.config.rabbitmq;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * RabbitConfig
 * Date: 04/02/2023
 *
 * @author Yauheni Antsipenka
 */
@Configuration
@Profile("rabbit")
public class RabbitConfig {

    @Value(value = "${create.notification.topic.name}")
    private String createNotificationTopicName;
    @Value(value = "${update.notification.topic.name}")
    private String updateNotificationTopicName;
    @Value(value = "${delete.notification.topic.name}")
    private String deleteNotificationTopicName;
    @Value(value = "${create.request.topic.name}")
    private String createRequestTopicName;
    @Value(value = "${update.request.topic.name}")
    private String updateRequestTopicName;
    @Value(value = "${delete.request.topic.name}")
    private String deleteRequestTopicName;

    @Value(value = "${create.notification.routing.key}")
    private String createNotificationRoutingKey;
    @Value(value = "${update.notification.routing.key}")
    private String updateNotificationRoutingKey;
    @Value(value = "${delete.notification.routing.key}")
    private String deleteNotificationRoutingKey;
    @Value(value = "${create.request.routing.key}")
    private String createRequestRoutingKey;
    @Value(value = "${update.request.routing.key}")
    private String updateRequestRoutingKey;
    @Value(value = "${delete.request.routing.key}")
    private String deleteRequestRoutingKey;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername("qwe");
        cachingConnectionFactory.setPassword("qwe");
        return cachingConnectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue createNotificationQueue() {
        return new Queue(createNotificationTopicName);
    }

    @Bean
    public Queue updateNotificationQueue() {
        return new Queue(updateNotificationTopicName);
    }

    @Bean
    public Queue deleteNotificationQueue() {
        return new Queue(deleteNotificationTopicName);
    }

    @Bean
    public Queue createRequestQueue() {
        return new Queue(createRequestTopicName);
    }

    @Bean
    public Queue updateRequestQueue() {
        return new Queue(updateRequestTopicName);
    }

    @Bean
    public Queue deleteRequestQueue() {
        return new Queue(deleteRequestTopicName);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("testExchange", true, false);
    }

    @Bean
    public Binding createNotificationBinding(DirectExchange exchange) {
        return BindingBuilder.bind(createNotificationQueue()).to(exchange).with(createNotificationRoutingKey);
    }

    @Bean
    public Binding updateNotificationBinding(DirectExchange exchange) {
        return BindingBuilder.bind(updateNotificationQueue()).to(exchange).with(updateNotificationRoutingKey);
    }

    @Bean
    public Binding deleteNotificationBinding(DirectExchange exchange) {
        return BindingBuilder.bind(deleteNotificationQueue()).to(exchange).with(deleteNotificationRoutingKey);
    }

    @Bean
    public Binding createRequestBinding(DirectExchange exchange) {
        return BindingBuilder.bind(createRequestQueue()).to(exchange).with(createRequestRoutingKey);
    }

    @Bean
    public Binding updateRequestBinding(DirectExchange exchange) {
        return BindingBuilder.bind(updateRequestQueue()).to(exchange).with(updateRequestRoutingKey);
    }

    @Bean
    public Binding deleteRequestBinding(DirectExchange exchange) {
        return BindingBuilder.bind(deleteRequestQueue()).to(exchange).with(deleteRequestRoutingKey);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }
}
