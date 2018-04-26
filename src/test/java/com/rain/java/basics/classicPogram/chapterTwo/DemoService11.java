package com.rain.java.basics.classicPogram.chapterTwo;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Administrator on 2017\11\15 0015.
 */
public class DemoService11 {
    //判断某一年是否为闰年
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int year;
        try {
            year =  scanner.nextInt();
            String nian = (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)? "闰年" : "平年" ;
            System.out.println(nian);
        }catch (Exception e){
            System.out.println("错误");
        }


    }
}
