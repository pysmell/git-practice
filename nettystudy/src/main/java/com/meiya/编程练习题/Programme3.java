package com.meiya.编程练习题;

import java.util.Scanner;

/*
现在给你一个整数N（2<N<1000），现在要求你写出一个程序，求出从1~N个数中的所有素数的和。
例如输入：3输出1~3的素数{2,3}的和：5
要求：使用循环结构完成，你需要定义一个isPrime方法用于判断一个数是否是素数。
* */
public class Programme3 {

    public static void main(String[] args) {

       Scanner scanner = new Scanner(System.in);
       int num = scanner.nextInt();
       int sum = isPrime(num);
       if (sum == -1) {

       } else {
           System.out.println("1~" + num + "个数中的所有素数的和:" + sum);
       }
    }

    public static int isPrime(int num) {

        int sum = 0;

        if (num <= 2  || num >= 1000) {
            return -1;
        }
        for (int j = 1; j <= num; j++) {

            if (j == 2 || j==3) {
                sum += j;
                continue;
            }
            for (int i = 2; i < Math.sqrt(j); i++) {
                if (j % i == 0) {
                    break;
                }
                if (j % i != 0 && i == (int)Math.ceil(Math.sqrt(j) - 1)) {
                    sum += j;
                }
            }
        }

        return sum;
    }
}
