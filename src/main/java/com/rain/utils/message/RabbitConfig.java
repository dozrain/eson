package com.rain.utils.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

//@Configuration
//@ConditionalOnExpression("${spring.rabbitmq.rabbitmq-enable:false}")
//public class RabbitConfig implements RabbitListenerConfigurer {
//
//    private static final Logger logger = LoggerFactory.getLogger(RabbitConfig.class);
//
//
//    public final static String EMAIL = "email"; //邮件
//
//    public final static String SMS = "sms"; //短信
//
//    public final static String WECHAR= "wechar"; //微信消息
//
//    public final static String PM = "pm"; //站内信
//
//    public final static String QQ = "qq"; //QQ站内信
//
//    public final static String ORTHER = "ORTHER"; //其他
//
//    @Resource
//    private AmqpAdmin amqpAdmin;
//    @Resource
//    private DefaultMessageHandlerMethodFactory factory;
//
//    @PostConstruct
//    public void initRabbitmq() {
//        try {
//            this.amqpAdmin.declareQueue(new Queue(EMAIL));
//            this.amqpAdmin.declareQueue(new Queue(SMS));
//            this.amqpAdmin.declareQueue(new Queue(WECHAR));
//            this.amqpAdmin.declareQueue(new Queue(PM));
//            this.amqpAdmin.declareQueue(new Queue(QQ));
//            this.amqpAdmin.declareQueue(new Queue(ORTHER));
//            logger.debug("messageservice init rabbitmq over");
//        } catch (Exception e) {
//            logger.error("messageservice init rabbitmq fail");
//        }
//    }
//
//    @Override
//    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
//        registrar.setMessageHandlerMethodFactory(factory);
//    }
//}
