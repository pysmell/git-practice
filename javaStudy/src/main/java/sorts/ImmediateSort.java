package sorts;

import java.util.Arrays;

/**
 * 直接插入排序 时间复杂度（平均）o（n^2） 稳定 时间复杂度（最好）o（n） 空间复杂度o（1）
 */
public class ImmediateSort {

    public static void main(String[] args) {

        int[] arr = new int[] {10, 9 ,30, 144, 60, 80, 90, 20, 30, 10};

        System.out.println(Arrays.toString(sort(arr)));

    }

    public static int[] sort(int[] arr) {

        int[] array = arr;

        for (int i = 1; i < array.length; i++) {

            int insertNode = array[i];

            int j = i - 1;

            while (j >= 0 && insertNode < array[j]) {

                array[j+1] = array[j];

                j--;
            }

            array[j+1] = insertNode;

        }


        return array;
    }
}
