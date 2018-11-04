package sorts;

import java.util.Arrays;

/**
 * 快速排序
 * 1、定义i=0,j=nums.length-1,i为第一个数的下标，j为最后一个数的下标
 * 2、从数组的最后一个数Aj从右往左找，找到第一个小于key的数，记为Aj
 * 3、从数组的第一个数Ai从左往右找，找到第一个大于key的数，记为Ai
 * 4、交换Ai和Aj
 * 5、重复这个过程，直到i=j
 * 6、调整key的位置，把A[i]和key交换
 */
public class FastSort {

    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 5, 7, 4, 5 ,3 ,9 ,0};
        quickSort(nums);
    }

    public static void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length-1);
    }

    private static void quickSort(int[] nums, int low, int high) {

        if (low >= high) {
            return;
        }

        int key = nums[low];

        int i = low;

        int j = high;

        while (i < j) {

            while (i < j && nums[j] > key) {

                j--;

            }

            while (i < j && nums[i] <= key) {
                i++;
            }

            if (i < j) {

                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
            }

        }

        int temp = nums[i];

        nums[i] = key;

        nums[low] = temp;

        quickSort(nums, low, i-1);

        quickSort(nums, i+1, high);

        System.out.println(Arrays.toString(nums));
    }


}





































































