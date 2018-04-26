package com.rain.service.java.designPattern.singleton;

/**
 * Created by Administrator on 2018-4-23 0023.
 */
public class SingletonEH {
    //创建唯一对象
    private static SingletonEH singletonEH = new SingletonEH();
    //私有构造函数,让类无法实例化
    public SingletonEH(){}
    //获取对象
    public static SingletonEH getSingletonEH(){
        return singletonEH;
    }

}
