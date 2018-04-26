package com.rain.service.java.designPattern.singleton;

/**
 * Created by Administrator on 2018-4-20 0020.
 */
public class SingletonLH {
    //创建一个SingleObject对象
    private  static SingletonLH singletonLH;
    //私有构造函数,让类无法实例化
    private SingletonLH(){}
    //获取唯一对象
    public static synchronized SingletonLH getSingletonLH(){
        if (singletonLH == null){
            singletonLH = new SingletonLH();
        }
        return singletonLH;
    }

    public void showMessage(){
        System.out.println("hello world");
    }
}
