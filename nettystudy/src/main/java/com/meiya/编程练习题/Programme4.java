package com.meiya.编程练习题;
/*
* 打印出所有“水仙花数”，所谓“水仙花数"是指一个三位数，其各位数字立方和等于该数本身。
* 例如：153是一个“水仙花数”，因为153=1的三次方+5的三次方+3的三次方
* */
public class Programme4 {

    public static void main(String[] args) {
        int sum = 0;
        for (int i = 100; i < 1000; i++) {

            int one =  i / 100;
            int two = (i / 10)%10;
            int three = i % 10;

            if (i == (one*one*one + two*two*two + three*three*three)) {
                sum += 1;
                System.out.println("sum:" + sum);
                System.out.println(i);
            }
        }

    }
}
