package com.rain.utils.threads;

/**
 * Created by mail on 2018\3\6 0006.
 */
public class ThreadUtil implements Runnable{
    public void run(){
        for(int i=0; i<=100;i++){
            System.out.println("ThreadUtil:"+i);
        }
    }
}
