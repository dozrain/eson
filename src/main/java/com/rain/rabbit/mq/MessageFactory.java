package com.rain.rabbit.mq;

import com.alibaba.fastjson.JSON;
import com.rain.model.dao.Message;
import com.rain.service.MessageService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018-7-9 0009.
 */
public class MessageFactory {

    @Resource
    RabbitSender rabbitSender;
    @Resource
    MessageService messageService;


    public void produceEmailMessage(EmailMessage emailMessage){
        Message message = new Message();
        message.setUserid(0l);
        message.setMsgtype(1);
        message.setFeature("0");
        message.setJsondata(JSON.toJSONString(emailMessage));
        messageService.insert(message);
        rabbitSender.send(message);
    }
    static class EMessage{
        //String messageType  //weixin，site，email
    }

    static class EmailMessage extends EMessage{
        String receiverAddress;
        String methodNumber;
        List<Object> methodParams;

        public String getReceiverAddress() {
            return receiverAddress;
        }

        public void setReceiverAddress(String receiverAddress) {
            this.receiverAddress = receiverAddress;
        }

        public String getMethodNumber() {
            return methodNumber;
        }

        public void setMethodNumber(String methodNumber) {
            this.methodNumber = methodNumber;
        }

        public List<Object> getMethodParams() {
            return methodParams;
        }

        public void setMethodParams(List<Object> methodParams) {
            this.methodParams = methodParams;
        }
    }
}
