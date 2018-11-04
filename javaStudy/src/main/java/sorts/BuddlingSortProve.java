package sorts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 冒泡排序增强
 */
public class BuddlingSortProve {

    public static void main(String[] args) {

        int[] a = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};

        System.out.println("排序之前：");

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }

        boolean flag = true;

        int k = a.length;

        while (flag) {

            flag = false;

            for (int i = 1; i < k; i++) {

                if (a[i - 1] > a[i]) {

                    flag = true;

                    int temp = a[i];

                    a[i] = a[i - 1];

                    a[i - 1] = temp;
                }
            }
            k--;
        }

        System.out.println(Arrays.toString(a));



}


}
