package com.rain.java.basics.designPattern.prototype;

/**
 * Created by Administrator on 2018-4-23 0023.
 */
public class Rectangle extends Shape {

    public Rectangle(){
        type =  "type";
    }
    @Override
    public void draw(){
        System.out.println("Inside Rectangle::draw() method.");
    }
}
