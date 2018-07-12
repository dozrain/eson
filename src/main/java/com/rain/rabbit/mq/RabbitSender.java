package com.rain.rabbit.mq;

import com.rain.model.dao.Message;
import com.rain.rabbit.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018-7-9 0009.
 */
@Component
public class RabbitSender {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send(Message message) {

        if (message.getMsgtype() == BusinessStatus.MESSAGE_TYPE_MAIL.intValue())
            this.rabbitTemplate.convertAndSend(RabbitConfig.MESSAGE_MAIL, message);
        else{
            this.rabbitTemplate.convertAndSend(RabbitConfig.MESSAGE_MAIL, message);
        }
    }
}
