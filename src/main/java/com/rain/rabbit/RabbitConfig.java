package com.rain.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    private static final Logger log = LoggerFactory.getLogger(RabbitConfig.class);

    public final static String MESSAGE_MAIL= "mail"; //邮件

    @Bean
    public Queue Queue() {
        return new Queue(MESSAGE_MAIL);
    }
}
