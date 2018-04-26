package com.rain.java.basics.classicPogram.chapterOne;

import java.io.FileNotFoundException;

/**
 * Created by Administrator on 2017\11\15 0015.
 */
public class DemoService10 {
    //        实现两个变量的互换（不借助第3个变量)
    public static void main(String[] args) throws FileNotFoundException {
            int a = 1;
            int b = 2;
            a = a^b;
            b = a^b;
            a = b^a;
        System.out.println("a:"+a+" b:"+b);
    }
}
