package com.rain.service.java.designPattern.prototype;

import java.util.Hashtable;

/**
 * Created by Administrator on 2018-4-23 0023.
 */
public class ShapeCache {

    private static Hashtable<String,Shape> shapeMap  = new Hashtable<String,Shape>();

    public static Shape getShape(String shapeId){
        Shape cachedShape = shapeMap.get(shapeId);
        return (Shape) cachedShape.clone();
    }

    //例如，我们要添加三种形状
    public static void loadCache() {
        Circle circle = new Circle();
        circle.setId("1");
        shapeMap.put(circle.getId(),circle);

        Square square = new Square();
        square.setId("2");
        shapeMap.put(square.getId(),square);

        Rectangle rectangle = new Rectangle();
        rectangle.setId("3");
        shapeMap.put(rectangle.getId(),rectangle);
    }
}
