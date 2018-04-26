package com.rain.java.basics.threads;

/**
 * Created by rain on 2018\3\7 0007.
 */
public class ThreadTest {
    public static void main(String args[]){
        new Station("窗口1").start();
        new Station("窗口2").start();
        new Station("窗口3").start();
    }
}
