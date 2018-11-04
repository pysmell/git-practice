package sorts;

import java.util.Arrays;

/**
 * 最优的冒泡排序
 */
public class BuddlingSortMoreProve {

    public static void main(String[] args) {

        int[] a = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};

        System.out.println("排序之前：");

        for (int i = 0; i < a.length; i++)
        {
            System.out.print(a[i] + " ");
        }

        int flag = a.length;

        while(flag > 0) {

            int k = flag;

            flag = 0;

            for (int i = 1; i < k; i++) {

                if (a[i-1] > a[i]) {
                    int temp = a[i];
                    a[i] = a[i-1];
                    a[i-1] = temp;
                    flag = k;
                }
            }

            k--;
        }
        System.out.println();
        System.out.println(Arrays.toString(a));
    }

}
