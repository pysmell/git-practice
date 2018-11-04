package sorts;

import java.util.Arrays;

/**
 * 冒泡排序  传统
 */
public class BuddlingSort {

    public static void main(String[] args) {

        int[] a = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};
        System.out.println("未排序");
        System.out.println(Arrays.toString(a));
        System.out.println("排好序");
        System.out.println(Arrays.toString(sort(a)));

    }

    public static int[] sort(int[] arr) {

        for (int i = 0; i < arr.length-1; i++) {

            for (int j = 0; j < arr.length - i -1 ;j++) {

                if (arr[j] > arr[j+1]) {
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }

            }
        }

        return arr;

    }

}
