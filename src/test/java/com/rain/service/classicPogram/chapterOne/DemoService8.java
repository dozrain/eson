package com.rain.service.classicPogram.chapterOne;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Administrator on 2017\11\15 0015.
 */
public class DemoService8 {
//    加密可以这样简单（位运算）
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        char [] arr = str.toCharArray();
        for(int i = 0; i < arr.length; i++){
            System.out.println("1:"+arr[i]);
            //arr[i] = (char)(arr[i]^20000);
            int test = (arr[i]^20000);
            System.out.println("2:"+test);
        }
    }
   
}
