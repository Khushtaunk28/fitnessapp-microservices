package com.fitness.activityService.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;



    @Bean
    public Queue queue() {
        return new Queue(queue,true);
    }


    @Bean
    public DirectExchange activityExchange() {
        return new DirectExchange(exchange);

    }

    @Bean
    public Binding activityBinding(Queue queue, DirectExchange activityExchange) {
        return BindingBuilder.bind(queue).to(activityExchange).with(routingKey);
    }

    @Bean
    public MessageConverter jsonbMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
