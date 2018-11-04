package sorts;

import java.util.Arrays;

/**
 * 希尔排序，选择一些步长（5，2，1）
 * 两元素之间的步长首先为5，比较这两个元素大小，较小的元素交换到左边，较大元素交换到右边
 * 然后步长为2
 * 接下来步长为1
 */
public class ShellSort {

    public static void main(String[] args) {

        int[] a = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};

        System.out.println("排序之前：");

        for (int i = 0; i < a.length; i++)
        {
            System.out.print(a[i] + " ");
        }

        int d = a.length;

        while (true) {

            d = d / 2;

            for (int x = 0; x < d; x++) {

                for (int i = x + d; i < a.length; i = i + d) {

                    int temp = a[i];

                    int j;

                    for (j = i - d;j >= 0 && a[j] > temp; j = j - d) {

                            a[j+d] = a[j];

                    }

                    a[j + d] = temp;

                }

            }

            if (d == 1) {
                break;
            }

        }
        System.out.println();

        System.out.println(Arrays.toString(a));
    }

}
























