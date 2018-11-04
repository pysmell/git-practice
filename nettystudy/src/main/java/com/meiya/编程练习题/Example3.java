package com.meiya.编程练习题;

/*
* 杨辉三角 输入position  得出相对应的杨辉三角
* */
public class Example3 {



    public static void main(String[] args) {
        printTriangle(8);
    }

    public static void printTriangle(int position) {

        Integer[][] integer = new Integer[1][position];

        Integer[][] temp = new Integer[1][position];

        if (position <= 0) {
            System.out.println("不合法的位置");
        }
        /*控制层数*/
        for (int i = 1; i <= position; i++) {

            /*控制每行有几个数字*/
                for (int j = 1; j <= i; j++) {
                    if (i == 1) {
                        System.out.print(i);
                    } else if (i == 2) {
                        integer[0][j-1] = 1;
                        System.out.print(integer[0][j-1]);
                        System.out.print("\t");
                    } else {
                        if (j == i) {
                            System.out.print(1);
                        } else if (j == 1) {
                            System.out.print(1);
                            System.out.print("\t");
                        } else {
                            int var = integer[0][j-2] + integer[0][j-1];
                            System.out.print(var);
                            System.out.print("\t");
                            temp[0][j-1] = var;
                        }
                    }
                }

            for (int j = 0; j < temp[0].length; j++) {
                if (temp[0][j] != null) {
                    integer[0][j] = temp[0][j];
                }
            }
            integer[0][i-1] = 1;
            System.out.print("\n");
        }
    }
}
