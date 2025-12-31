package demoapp.newjwtspringsecurity.rabbitmq.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitConfig {
    @Value("${queue.name}")
    private String queueName;

    @Value("${spring.rabbitmq.username}")
    private String userName;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Bean
    public Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    public CachingConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername(userName);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    FanoutExchange fourthExchange() {
        return new FanoutExchange("fourth.exchange");
    }

    @Bean
    Queue queueA() {
        return new Queue("FourthQueue.A");
    }

    @Bean
    Queue queueB() {
        return new Queue("FourthQueue.B");
    }

    @Bean
    Binding bindA(FanoutExchange exchange) {
        return BindingBuilder.bind(queueA()).to(exchange);
    }

    @Bean
    Binding bindB(FanoutExchange exchange) {
        return BindingBuilder.bind(queueB()).to(exchange);
    }
}
