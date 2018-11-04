package com.meiya.编程练习题;

import java.util.Scanner;

/*
* 题目：古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到3个月后每个月又生一对兔子，假如兔子都不死
* 每个月的兔子对数为多少？
* */
public class Programme1 {

    public static void main(String[] args) {

        System.out.println("请输入你想知道的兔子数量的月份：");

        Scanner scanner = new Scanner(System.in);

        int month = scanner.nextInt();

        getNum(month);
    }

    public static int getNum (int month) {
        if (month == 1 || month == 2) {
            return 1;
        } else {
            return getNum(month -1) + getNum(month - 2);
        }
    }
}
