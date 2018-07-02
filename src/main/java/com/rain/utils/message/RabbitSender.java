package com.rain.utils.message;


import com.rain.model.Message;
import com.rain.model.pojo.BusinessStatus;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ron
 *         2016/5/18.
 */
@Service
public class RabbitSender {
    @Resource
    private RabbitMessagingTemplate rabbitMessagingTemplate;

    public void sendToRabbitmq(Message message) {

//        if (message.getMsgtype() == BusinessStatus.MESSAGE_TYPE_MAIL.intValue()) {//邮件渠道
//            rabbitMessagingTemplate.convertAndSend(RabbitConfig.EMAIL, message);
//        } else if (message.getMsgtype() == BusinessStatus.MESSAGE_TYPE_SMS.intValue()) {//短信渠道
//            rabbitMessagingTemplate.convertAndSend(RabbitConfig.SMS, message);
//        } else if (message.getMsgtype() == BusinessStatus.MESSAGE_TYPE_WECHAR.intValue()) {//微信渠道
//            rabbitMessagingTemplate.convertAndSend(RabbitConfig.WECHAR, message);
//        }else if (message.getMsgtype() == BusinessStatus.MESSAGE_TYPE_PM.intValue()) {//站内信渠道
//            rabbitMessagingTemplate.convertAndSend(RabbitConfig.PM, message);
//        }else if (message.getMsgtype() == BusinessStatus.MESSAGE_TYPE_QQ.intValue()) {//回复至QQ渠道
//            rabbitMessagingTemplate.convertAndSend(RabbitConfig.QQ, message);
//        }else {
//            rabbitMessagingTemplate.convertAndSend(RabbitConfig.ORTHER, message);
//        }
    }
}
