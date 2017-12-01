package com.rain.service.classicPogram.chapterOne;

import java.io.FileNotFoundException;

/**
 * Created by Administrator on 2017\11\15 0015.
 */
public class DemoService6 {
//    自动类型转换与强制类型转换
    public static void main(String[] args) throws FileNotFoundException {
        byte be = 1;
        short st = 10;
        int it = 100;
        long lg = 1000;
        float ft = 12.123123123123f;
        double db = 12.12;
        char cr = '1';
        System.out.println("byte"+be);
        System.out.println("byte"+(be+st));
        System.out.println("byte"+(be+st+it));
        System.out.println("byte"+(be+st+it+lg));
        System.out.println("byte"+(be+st+it+lg+cr));
        System.out.println("byte"+(be+st+it+lg+cr+db));


    }
    public static void test(String str){
        String ad = "qerasdqewr"+str;
        System.out.print(ad);

    }
}
