package demoapp.newjwtspringsecurity.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQTopicConfig {
    public static final String QUEUE = "internal.queue";
    public static final String EXCHANGE = "internal.exchange";
    public static final String ROUTING_KEY = "internal.routing-key";

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue internalQueue() {
        return QueueBuilder.durable("internal.queue").build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
            .bind(internalQueue())
            .to(topicExchange())
            .with(ROUTING_KEY);
    }
}
