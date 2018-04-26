package com.rain.java.basics.threads;

/**
 * Created by Administrator on 2018\3\28 0028.
 */
public class Bank {
    //假设一个账户有1000块钱
    static int money = 1000;
    //柜台取钱
    public void Counter(int money) {// 参数是每次取走的钱
        Bank.money -= money;//取钱后总数减少
        System.out.println("A取走了" + money + "还剩下" + (Bank.money));
    }
    public void ATM(int money) {// 参数是每次取走的钱
        Bank.money -= money;//取钱后总数减少
        System.out.println("B取走了" + money + "还剩下" + (Bank.money));
    }
}
