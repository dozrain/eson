package com.rain.rabbit.mq;

import com.alibaba.fastjson.JSON;
import com.rain.model.dao.Message;
import com.rain.rabbit.RabbitConfig;
import com.rain.rabbit.mail.MailServiceHelper
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import javax.annotation.Resource
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018-7-9 0009.
 */
@Component
public class RabbitReceiver {
    private static final Logger log = LoggerFactory.getLogger(RabbitReceiver.class);

    @Resource
    MailServiceHelper mailServiceHelper;

    @RabbitListener(queues = RabbitConfig.MESSAGE_MAIL)
    public void ProcessMail(Message message) {

        MessageFactory.EmailMessage emailMessage = JSON.parseObject(message.getJsondata(),MessageFactory.EmailMessage);

        if(!emailMessage || !emailMessage.receiverAddress){
            log.error("{}", "---处理失败 " + message.getClass().toString() + "消息, 消息数据异常")
            return
        }
        if("00".equals(emailMessage.methodNumber)){
            if(emailMessage.methodParams && emailMessage.methodParams.size() >= 3){
                mailServiceHelper.sendEmailByJavaMailSender(emailMessage.receiverAddress, emailMessage.methodParams[0], emailMessage.methodParams[1], emailMessage.methodParams[2])
                return;
            }else{
                updateMessage(message1)
                log.error("{}", "---处理失败 " + message.getClass().toString() + "消息, 消息数据异常")
                return;
            }
        }
        Method method = mailServiceHelper.getClass().getMethods().find { it ->
            it.name.equals("sendTempMessage${emailMessage.methodNumber}".toString())
        }
        if(method){
            def args = [emailMessage.receiverAddress]
            args.addAll(emailMessage.methodParams)
            Object result = method.invoke(mailServiceHelper, args as Object[])
            log.info("{}", "---处理完成 " + message.getClass().toString() + "消息, result：" + result)
        }else{
            updateMessage(message1)
            log.error("{}", "---处理失败 " + message.getClass().toString() + "消息, sendTempMessage${message.methodNumber} 未找到。")
        }
    }
}
