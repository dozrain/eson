package com.rain.service.classicPogram.chapterTwo;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Administrator on 2017\11\15 0015.
 */
public class DemoService13 {
    //为新员工分配部门
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        String password = scanner.nextLine();
        if(!username.equals("zhanghao")){
            System.out.println("用户账号不符");
        }else if(!password.equals("mima")){
            System.out.println("密码不正确");
        }else{
            System.out.println("通过认证");
        }
    }
}
