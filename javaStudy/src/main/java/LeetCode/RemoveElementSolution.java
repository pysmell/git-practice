package LeetCode;

import java.util.Arrays;

/**
 * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 */
public class RemoveElementSolution {

    public static void main(String[] args) {

        int[] nums = {3, 2, 2, 3};

        int val = 3;

        new RemoveElementSolution().removeElement(nums, val);
    }

    public int removeElement(int[] nums, int val) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            if (nums[0] == val) {
                return 0;
            } else {
                return 1;
            }
        }

        int len = 0;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] == val) {

                continue;

            } else {

                nums[len] = nums[i];

                len++;

            }

        }

        System.out.println(Arrays.toString(nums));

        System.out.println(len);

        return len;
    }

}
