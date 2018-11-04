package com.meiya;


/*
* 0,1,1,2,3,5,8,13,21......
* 输入position找出对应的值
* position从1开始
*
* */
public class Example1 {

    private static Integer[] integers = {0, 1};

    public static int getNumber(int position) {

        if (position < 0) {
            return -1;
        } else if (position == 1 || position == 2) {
            return integers[position-1];
        } else {
            int flag = position - 2;
            int beforeNum = integers[1];
            int temp = 0;
            int sum = integers[0] + integers[1];
            if (flag == 1) {
                return sum;
            }
            for (int i = 1; i < flag; i++) {
                temp = sum;
                sum = sum + beforeNum;
                beforeNum = temp;
            }

            return sum;
        }
    }

    public static void main(String[] args) {
        System.out.println(getNumber(10));
    }

}
