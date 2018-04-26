package com.rain.service.java.designPattern.prototype;

/**
 * Created by Administrator on 2018-4-23 0023.
 */
public class Circle extends Shape {
    public Circle(){
        type = "Circle";
    }

    public void draw(){
        System.out.println("Inside Circle::draw() method.");
    }
}
