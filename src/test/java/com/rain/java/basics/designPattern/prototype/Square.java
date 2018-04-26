package com.rain.java.basics.designPattern.prototype;

/**
 * Created by Administrator on 2018-4-23 0023.
 */
public class Square extends Shape{

    public Square(){
        type = "Square";
    }

    public void draw(){
        System.out.println("Inside Square::draw() method.");
    }
}
