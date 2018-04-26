package com.rain.java.frame.messageQueue;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 在这里我们以控制台输入信息模拟窗口、对话框接收鼠标、键盘等消息，
 * 以ArrayBlockingQueue对象存放消息队列。在控制台中输入一个数值和一个字符串代表一个消息，输入-1结束输入。模拟代码如下：
 */
public class WindowSimulator implements MessageProcess {
    private ArrayBlockingQueue msgQueue;

    public WindowSimulator(ArrayBlockingQueue msgQueue){
        this.msgQueue = msgQueue;
    }

    public void GenerateMsg() {
        while(true) {
            Scanner scanner = new Scanner(System.in);
            int msgType = scanner.nextInt();
            if(msgType < 0) {           //输入负数结束循环
                break;
            }
            String msgInfo = scanner.next();
            Message msg = new Message(this, msgType, msgInfo);
            try {
                msgQueue.put(msg);      //新消息加入到队尾
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void doMessage(Message msg){
        switch (msg.getType()){
            case Message.KEY_MSG : onKeyDown(msg);
                break;
            case  Message.MOUSE_MSG: onMouseDown(msg);
                break;
            default:
                onSysEvent(msg);
        }
    }

    //键盘事件
    public static void onKeyDown(Message msg) {
        System.out.println("键盘事件：");
        System.out.println("type:" + msg.getType());
        System.out.println("info:" + msg.getInfo());
    }

    //鼠标事件
    public static void onMouseDown(Message msg) {
        System.out.println("鼠标事件：");
        System.out.println("type:" + msg.getType());
        System.out.println("info:" + msg.getInfo());
    }

    //操作系统产生的消息
    public static void onSysEvent(Message msg) {
        System.out.println("系统事件：");
        System.out.println("type:" + msg.getType());
        System.out.println("info:" + msg.getInfo());
    }
}
