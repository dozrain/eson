package com.rain.service.mq

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.annotation.Resource
/**
 * @author ron
 *         2016/5/18.
 */
@Service
public class RabbitReceiver {
    private static final Logger logger = LoggerFactory.getLogger(RabbitReceiver.class);

    @Resource
    MessageService messageService


    //报价单处理
//    @RabbitListener(queues = RabbitConfig.WECHAR)
//    public void receiveProduceBjdMessage(Message message){
////        MessageUtil.WecharMessage = JSON.parseObject(message.getJsondata(),MessageUtil.WecharMessage)
//    }
//
//    private void updateMessage(Message message){
//        if (!message){
//            return
//        }
//        String count = message.feature
//        if (count){
//            Integer c = Integer.parseInt(count)
//            if (c >0 && c< 5){
//                c = c + 1;
//                message.setFeature(c as  String);
//                messageService.updateByPrimaryKeySelective(message);
//            }
//        }else{
//            message.setFeature("1");
//            messageService.updateByPrimaryKeySelective(message);
//        }
//    }
}
