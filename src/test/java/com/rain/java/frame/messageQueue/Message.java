package com.rain.java.frame.messageQueue;

/**
 * 何为消息？消息就是带有某种信息的信号，如你用鼠标点击一个窗口会产生鼠标的消息，键盘输入字符会产生键盘的消息，
 * 一个窗口大小的改变也会产生消息。消息从何而来?根据冯·诺依曼的体系结构计算机有运算器、存储器、控制器和输入设备和输出设备
 * 五大部件组成，消息主要来自输入设备，如键盘、鼠标、扫描仪等，也可来自已窗口和操作系统。消息机制的三大要点：
 * 消息队列、消息循环(分发)、消息处理。其结构如下：
 */
public class Message {
    //消息类型
    public static final int KEY_MSG = 1;
    public static final int MOUSE_MSG = 2;
    public static final int SYS_MSG = 2;

    private Object source; //来源
    private int type;      //类型
    private String info;   //消息

    public  Message(Object source,int type,String info){
        super();
        this.source = source;
        this.type = type;
        this.info = info;
    }

    public static int getKeyMsg() {
        return KEY_MSG;
    }

    public static int getMouseMsg() {
        return MOUSE_MSG;
    }

    public static int getSysMsg() {
        return SYS_MSG;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
