package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSolution2 {

    public static void main(String[] args) {

        int[] nums = {-1, 0, 1, 2, -1, -4};  //-4 -1 -1 0 1 2

        List<List<Integer>> lists = threeSum(nums);

        for (List<Integer> list : lists) {

            System.out.println(Arrays.toString(list.toArray()));
        }

    }

    public static  List<List<Integer>> threeSum(int[] nums) {

        quickSort(nums, 0, nums.length-1);

        List<List<Integer>> listList = new ArrayList<>();

        if (nums.length == 0 || nums[0] > 0 || nums[nums.length-1] < 0) {
            return listList;
        }

        for (int i = 0;i < nums.length-2;i++) {

            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            int start = i + 1;

            int end = nums.length - 1;

            while (start < end) {

                if ((start > (i+1)) && (nums[start] == nums[start-1])) {
                    start++;
                    continue;
                }

                if (nums[i] + nums[start] + nums[end] == 0) {

                    List<Integer> list = new ArrayList<>();

                    list.add(nums[i]);

                    list.add(nums[start]);

                    list.add(nums[end]);

                    listList.add(list);

                    start++;
                    continue;
                }

                if (nums[i] + nums[start] + nums[end] > 0) {

                    end--;

                }

                if (nums[i] + nums[start] + nums[end] < 0) {

                    start++;

                }
            }

        }

        return listList;
    }

    private static void quickSort(int[] nums, int low, int high) {

        if (low >= high) {
            return;
        }

        int key = nums[low];

        int i = low;

        int j = high;

        while (i < j) {

            while (i < j && nums[j] > key)
                j--;

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

    }

}
