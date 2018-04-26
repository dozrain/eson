package com.rain.service.java.designPattern.singleton;

/**
 * Created by Administrator on 2018-4-20 0020.
 */
public class SingletonDCL {
    //创建一个SingleObject对象
    private volatile  static  SingletonDCL singletonDCL;
    //私有构造函数,让类无法实例化
    private SingletonDCL(){}
    //获取唯一对象

    public static SingletonDCL getSingletonDCL() {
        if (singletonDCL == null){
            synchronized (SingletonLH.class){
                if(singletonDCL == null){
                    singletonDCL =   new SingletonDCL();
                }
            }
        }
        return singletonDCL;
    }
}
