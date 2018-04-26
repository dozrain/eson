package com.rain.service.java.designPattern.singleton;

/**
 * Created by Administrator on 2018-4-23 0023.
 */
public class SingletonJT {
    private static class  SingletonHolder{
        private static SingletonJT singletonJT = new SingletonJT();
    }
    private SingletonJT(){}
    public static final SingletonJT getSingletonJT(){
        return SingletonHolder.singletonJT;
    }

}
