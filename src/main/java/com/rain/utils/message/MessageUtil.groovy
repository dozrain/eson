package com.rain.utils.message

import com.alibaba.fastjson.JSON
import com.rain.model.dao.Message
import com.rain.service.mq.MessageService
import org.springframework.stereotype.Component
import javax.annotation.Resource

/**
 * Created by tpeng on 2017/6/15.
 */
@Component
class MessageUtil{
    @Resource
    MessageService messageService
    @Resource
    RabbitSender rabbitSender

    /* private static Executor pool = Executors.newFixedThreadPool(10);

     private static BlockingQueue messageQueue = new ArrayBlockingQueue(10);*/

    void wecharMessage(WecharMessage message){
//        pool.execute(new AogMessageFactory.MessageProducer(message));
        Message wecharMessage = new Message()
        wecharMessage.userid=0;
        wecharMessage.msgtype=3
        wecharMessage.feature="0"
        wecharMessage.jsondata= JSON.toJSONString(message)
        messageService.insert(wecharMessage);
        rabbitSender.sendToRabbitmq(wecharMessage)
    }

    void produceSiteMessage(SiteMessage message){
        /* pool.execute(new AogMessageFactory.MessageProducer(message));*/
        Message message1 = new Message()
        message1.userid=0;
        message1.msgtype=4;
        message1.feature="0"
        message1.jsondata= JSON.toJSONString(message)
        messageService.insert(message1)
        rabbitSender.sendToRabbitmq(message1)
    }

    void emailMessage(EmailMessage message){
        /* pool.execute(new AogMessageFactory.MessageProducer(message));*/
        Message emailMessage = new Message()
        emailMessage.userid=0
        emailMessage.msgtype=1
        emailMessage.feature="0"
        emailMessage.jsondata= JSON.toJSONString(message)
        messageService.insert(emailMessage)
        rabbitSender.sendToRabbitmq(emailMessage)
    }

    void qqMessage(QQMessage message){
        /* pool.execute(new AogMessageFactory.MessageProducer(message));*/
        Message qqMessage = new Message()
        qqMessage.userid=0
        qqMessage.msgtype=5
        qqMessage.feature3="0"
        qqMessage.jsondata= JSON.toJSONString(message)
        messageService.insert(qqMessage)
        rabbitSender.sendToRabbitmq(qqMessage)
    }

    /*  @Override
      void run(String... args) throws Exception {
          pool.execute(new AogMessageFactory.MessageCustomer());
      }*/

    static class EMessage{
        //String messageType  //weixin，site，email
    }

    static class WecharMessage extends EMessage{
        String publicOpenid
        String methodNumber
        List<Object> methodParams
    }

    static class SiteMessage extends EMessage{
        String userId
        String methodNumber
        List<Object> methodParams
    }

    static class EmailMessage extends EMessage{
        String receiverAddress
        String methodNumber
        List<Object> methodParams
    }

    static class QQMessage extends EMessage{
        Long  quickId
        String qqContent
    }

/*

    private class MessageProducer implements Runnable{

        MessageProducer(EMessage message){
            this.eMessage = message
        }

        private EMessage eMessage

        private void produce(){
            try {
                messageQueue.put(eMessage);
                log.info("{}", "---put " + eMessage.getClass().toString() + "消息")
            }catch(InterruptedException ie) {
                log.error("{}", "中断异常---put error " + eMessage.getClass().toString() + "消息")
                ie.printStackTrace();
            }
        }

        @Override
        public void run() {
            produce();
        }
    }

    private class MessageCustomer implements Runnable{

        private void consume() {
            try {
                if(messageQueue.size() == 0) {
                    log.info("{}", "--- 消息已空，等待新消息。")
                }
                EMessage message0 = messageQueue.take()
                log.info("{}", "---get " + message0.getClass().toString() + "消息, 准备处理")
                if(message0 instanceof WeiXinMessage){
                    WeiXinMessage message = (WeiXinMessage)message0
                    if(!message || !message.publicOpenid){
                        log.error("{}", "---处理失败 " + message.getClass().toString() + "消息, 消息数据异常")
                        return
                    }
                    Method method = weiXinMessageService.getClass().getMethods().find { it ->
                        it.name.equals("sendTempMessage${message.methodNumber}".toString())
                    }
                    if(method){
                        def args = [message.publicOpenid]
                        args.addAll(message.methodParams)
                        Object result = method.invoke(weiXinMessageService, args as Object[])
                        log.info("{}", "---处理完成 " + message.getClass().toString() + "消息, result：" + result)
                    }else{
                        log.error("{}", "---处理失败 " + message.getClass().toString() + "消息, sendTempMessage${message.methodNumber} 未找到。")
                    }
                }else if(message0 instanceof SiteMessage){
                    SiteMessage message = (SiteMessage)message0
                    if(!message || !message.userId){
                        log.error("{}", "---处理失败 " + message.getClass().toString() + "消息, 消息数据异常")
                        return
                    }
                    Method method = messageServiceHelper.getClass().getMethods().find { it ->
                        it.name.equals("sendTempMessage${message.methodNumber}".toString())
                    }
                    if(method){
                        def args = [message.userId]
                        args.addAll(message.methodParams)
                        Object result = method.invoke(messageServiceHelper, args as Object[])
                        log.info("{}", "---处理完成 " + message.getClass().toString() + "消息, result：" + result)
                    }else{
                        log.error("{}", "---处理失败 " + message.getClass().toString() + "消息, sendTempMessage${message.methodNumber} 未找到。")
                    }
                }else if(message0 instanceof EmailMessage){
                    EmailMessage message = (EmailMessage)message0
                    if(!message || !message.receiverAddress){
                        log.error("{}", "---处理失败 " + message.getClass().toString() + "消息, 消息数据异常")
                        return
                    }
                    if(message.methodNumber.equals("00")){
                        if(message.methodParams && message.methodParams.size() >= 3){
                            mailServiceHelper.sendEmailByJavaMailSender(message.receiverAddress, message.methodParams[0], message.methodParams[1], message.methodParams[2])
                            log.info("{}", "---处理完成 " + message.getClass().toString() + "消息")
                            return;
                        }else{
                            log.error("{}", "---处理失败 " + JSONObject.toJSONString(message) + "消息, 消息数据异常")
                            return
                        }
                    }
                    Method method = mailServiceHelper.getClass().getMethods().find { it ->
                        it.name.equals("sendTempMessage${message.methodNumber}".toString())
                    }
                    if(method){
                        def args = [message.receiverAddress]
                        args.addAll(message.methodParams)
                        Object result = method.invoke(mailServiceHelper, args as Object[])
                        log.info("{}", "---处理完成 " + message.getClass().toString() + "消息, result：" + result)
                    }else{
                        log.error("{}", "---处理失败 " + message.getClass().toString() + "消息, sendTempMessage${message.methodNumber} 未找到。")
                    }
                }else{
                    log.error("{}", "未知的消息类型---error " + message0.getClass().toString() + "")
                }
            }catch(Exception ie) {
                log.error("{}", "中断异常---put error 消息处理")
                ie.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (true){
                consume();
            }
        }
    }
*/

    //启动生产者和消费者线程
    public void start() {
        /*  for (int i = 1; i < 5; i++) {
              pool.execute(new MessageProducer());
          }*/

    }

    public static void main(String[] args) {
        /*  AogMessageFactory pc = new AogMessageFactory();
          pc.start();*/
        def methodNumber = '13'
        if("sendTempMessage13".equals("sendTempMessage${methodNumber}".toString())){
            println "----------"
        }
        println "0000000000000000"
    }
}
