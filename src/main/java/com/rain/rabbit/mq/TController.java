package com.rain.rabbit.mq;


import com.alibaba.fastjson.JSON;
import com.rain.model.dao.Message;
import com.rain.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class TController {

    @Autowired
    MessageService messageService;

    @Autowired
    RabbitSender rabbitSender;

    @GetMapping(value = "/test")
    public void sendM() throws Exception {
        List list = new ArrayList();
        list.add("ceshi");
        list.add("ceshi");
        list.add(null);
        MessageFactory.EmailMessage emailMessage = new MessageFactory.EmailMessage();
        emailMessage.setReceiverAddress("393253889@qq.com");
        emailMessage.setMethodNumber("00");
        emailMessage.setMethodParams(list);
        Message message = new Message();
        message.setJsondata(JSON.toJSONString(emailMessage));
        message.setUserid(0l);
        message.setMsgtype(0);
//        messageService.insert(message);
        rabbitSender.send(message);
    }
}
