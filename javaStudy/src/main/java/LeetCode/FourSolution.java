package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 四数之和
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，
 * 判断nums中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？
 * 找出所有满足条件且不重复的四元组
 */
public class FourSolution {

    public static void main(String[] args) {

        int[] nums = {1, 0, -1, 0, -2, 2};

        List<List<Integer>> lists = fourSum(nums, 0);

        for (List<Integer> list : lists) {

            System.out.println(Arrays.toString(list.toArray()));

        }
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> lists = new ArrayList<>();

        if (nums.length == 0) {

            return lists;

        }


        //快速排序
        for (int i = 1; i < nums.length; i++) {

            int insertNode = nums[i];

            int j = i-1;

            while (j >= 0&&nums[j] > insertNode) {

                nums[j+1] = nums[j];

                j--;
            }
            nums[j+1] = insertNode;
        }

        for (int i = 0;i < nums.length - 3;i++) {

            if (i > 0 && nums[i] == nums[i-1]) {

                continue;

            }

            for (int j = i+1;j < nums.length - 2;j++) {

                if (j > i+1 && nums[j] == nums[j-1]) {
                    continue;
                }

                int start = j+1;

                int end = nums.length - 1;

                while (start < end) {

                    if (start > j+1 && nums[start] == nums[start-1]) {
                        start++;
                        continue;
                    }

                    if (nums[i] + nums[j] + nums[start] + nums[end] == target) {

                        List<Integer> integerList = new ArrayList<>();

                        integerList.add(nums[i]);

                        integerList.add(nums[j]);

                        integerList.add(nums[start]);

                        integerList.add(nums[end]);

                        lists.add(integerList);

                        start++;

                        continue;
                    }

                    if (nums[i] + nums[j] + nums[start] + nums[end] < target) {

                        start++;
                    }

                    if (nums[i] + nums[j] + nums[start] + nums[end] > target) {
                        end--;
                    }

                }

            }

        }

        return lists;
    }
}
