package LeetCode;

import java.util.Arrays;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，
 * 返回移除后数组的新*长度。不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1)
 * 额外空间的条件下完成。
 */
public class RemoveDuplicatesSolution {

    public static void main(String[] args) {

        int[] nums = {0, 0, 1, 1, 1, 1, 1, 2 ,2, 2, 3, 3, 4};

        System.out.println(new RemoveDuplicatesSolution().removeDuplicates(nums));

    }

/*
    public int removeDuplicates(int[] nums) {

        int equalNums = 0;

        System.out.println(Arrays.toString(nums));

        for (int i = 1; i < nums.length-equalNums; i++) {

            if (nums[i] == nums[i-1]) {

                for(int j = i+1;j < nums.length;j++) {

                    nums[j-1] = nums[j];

                }

                equalNums += 1;

                System.out.println(Arrays.toString(nums));

                if (nums[i] == nums[i-1]) {
                    i--;
                }
            }

        }

        System.out.println(Arrays.toString(nums));

        return nums.length - equalNums;

    }
*/

    public int removeDuplicates(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return 1;
        }

        int temp = nums[0];

        int len = 1;

        for (int i = 1; i < nums.length; i++) {

            if (temp == nums[i]) {
                continue;
            } else {
                nums[len] = nums[i];
                temp = nums[i];
                len++;
            }

        }

        System.out.println(Arrays.toString(nums));

        return len;

    }

}
