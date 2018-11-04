package LeetCode;

import java.util.Arrays;

/**
 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，
 使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 */
public class SimilarityThreeSolution {

    public static void main(String[] args) {

        int[] nums = new int[]{-1, 2, 1, -4};  //-4 -1 1 2

        System.out.println(threeSumClosest(nums, 1));
    }

    public static int threeSumClosest(int[] nums, int target) {

        int similarityValue = 0;

        int first = 0;

        int flag = nums.length;

        while (flag > 0) {

            int k = flag;

            flag = 0;

            for (int i = 1;i < k;i++) {

                if (nums[i-1] > nums[i]) {

                    int temp = nums[i];

                    nums[i] = nums[i-1];

                    nums[i-1] = temp;
                    flag = k;
                }

            }

        }


        for (int i = 0; i < nums.length-2; i++) {

            for (int j = i + 1; j < nums.length-1; j++) {

                int temp = target-nums[i]-nums[j];

                int start = j + 1;

                int end = nums.length-1;

                while (start <= end) {

                    if (nums[end] <= temp) {

                        if (first==0) {

                            similarityValue = nums[i] + nums[j] + nums[end];
                            first += 1;

                        } else {

                            int value = nums[i] + nums[j] + nums[end];

                            if (Math.abs((value-target)) < Math.abs(similarityValue - target)) {

                                similarityValue = value;

                            }

                        }
                        break;
                    }

                    if (nums[start] >= temp) {

                        if (first == 0) {
                            similarityValue = nums[i] + nums[j] + nums[start];
                            first = 1;
                        } else {

                            int value = nums[i] + nums[j] + nums[start];

                            if (Math.abs((value-target)) < Math.abs(similarityValue - target)) {

                                similarityValue = value;

                            }

                        }
                        break;
                    }

                    int middle = start + (end-start)/2;

                    if (temp >= nums[middle] && temp <= nums[middle+1]) {

                        if (Math.abs((nums[middle]-temp)) < Math.abs((nums[middle+1] - temp))) {

                            if (first == 0) {
                                first += 1;
                                similarityValue = nums[i] + nums[j] + nums[middle];
                            } else {

                                int value = nums[i] + nums[j] + nums[middle];

                                if (Math.abs((value-target)) < Math.abs(similarityValue - target)) {

                                    similarityValue = value;

                                }

                            }
                            break;
                        } else {
                            if (first == 0) {
                                first = 1;
                                similarityValue = nums[i] + nums[j] + nums[middle+1];
                            } else {

                                int value = nums[i] + nums[j] + nums[middle+1];

                                if (Math.abs((value-target)) < Math.abs(similarityValue - target)) {

                                    similarityValue = value;

                                }

                            }
                            break;
                        }

                    }
                    if (temp>=nums[middle]) {
                        start = middle;
                    }

                    if (temp <= nums[middle]) {
                        end = middle;
                    }
                    System.out.println("12123" +similarityValue);
                }


            }

        }

        return similarityValue;

    }

}
