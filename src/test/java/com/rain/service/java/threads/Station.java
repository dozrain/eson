package com.rain.service.java.threads;

/**
 * Created by Administrator on 2018\3\28 0028.
 */
public class Station extends Thread{
    // 为了保持票数的一致，票数要静态
    static int tick = 20;
    // 创建一个静态钥匙
    static Object lock = "lock";
    // 通过构造方法给线程名字赋值
    public Station(String name) {
        super(name);// 给线程名字赋值
    }
    @Override
    public void run(){
        System.out.println(getName()+"测试");
        while (tick>0){
            synchronized (lock){
                if (tick>0){
                    System.out.println(getName() + "卖出了第" + tick + "张票");
                    tick--;
                }else{
                    System.out.println("票买完了");
                }
                try {
                    sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
