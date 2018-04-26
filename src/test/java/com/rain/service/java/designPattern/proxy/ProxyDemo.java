package com.rain.service.java.designPattern.proxy;

/**
 * Created by Administrator on 2018-4-24 0024.
 */
public class ProxyDemo {
    public static void main(String[] args) {
        Image image = new StaticProxy("test_10mb.jpg");

        image.display();
        System.out.println(" ");
        image.display();
    }
}
