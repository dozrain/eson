package com.rain.service.classicPogram.chapterOne;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Created by Administrator on 2017\11\15 0015.
 */
public class DemoService5 {
//    重定向输出流实现程序日志
    public static void main(String[] args) {
        String filePath = "F:\\null\\log.txt";
        try {
            PrintStream out = System.out;
            PrintStream  ps = new PrintStream(filePath);
            System.setOut(ps);
            int age = 18;
            System.out.println("初始值为18");
            String sex = "women";
            System.out.println("sex init women");
            String info = age + sex;
            System.setOut(out);
            System.out.println("程序运行完毕，请查看日志文件。");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    };
}
