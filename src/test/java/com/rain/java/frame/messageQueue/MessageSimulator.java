package com.rain.java.frame.messageQueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 这里模拟用例中只有一个消息输入源，且是一种线程阻塞的，只有输入结束后才会进行消息的处理。
 * 真实的Windows操作系统中的消息机制会有多个消息输入源，且消息输入的同时也能进行消息的处理。
 */
public class MessageSimulator {
    //消息队列
    private static ArrayBlockingQueue<Message> messageQueue = new ArrayBlockingQueue<Message>(100);

    public static void main(String[] args) {
        WindowSimulator generator = new WindowSimulator(messageQueue);
        //产生消息
        generator.GenerateMsg();

        //消息循环
        Message msg = null;
        while((msg = messageQueue.poll()) != null) {
            ((MessageProcess) msg.getSource()).doMessage(msg);
        }
    }
}
