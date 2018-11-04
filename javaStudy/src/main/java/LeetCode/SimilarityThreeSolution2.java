package LeetCode;

import java.util.Arrays;

/**
 * 最接近的三数之和
 */
public class SimilarityThreeSolution2 {

    public static void main(String[] args) {

        int[] nums = new int[]{-1, 2, 1, -4};

        System.out.println(threeSumClosest(nums, 1));
    }

    public static int threeSumClosest(int[] nums, int target) {

        int d = nums.length;

        //最小差值
        int minimumDifference = Integer.MAX_VALUE;

        //每次减完的差值
        int differenceValue = 0;

        //最接近的和
        int result = 0;

        //每次的和
        int sum = 0;

        while(true) {

            d = d / 2;

            for (int i = 0;i < d;i++) {

                for (int j = i+d; j < nums.length;j=j+d) {

                    int temp = nums[j];

                    int k;

                    for (k = j-d;k>=0&&nums[k]>temp;k=k-d) {

                        nums[k+d] = nums[k];
                        System.out.println();
                    }

                    nums[k+d] = temp;

                }

            }

            if (d == 1) {

                break;

            }

        }

        for (int i = 0; i < nums.length-2; i++) {

            int start = i + 1;

            int end = nums.length - 1;

            while (start < end) {

                sum = nums[i] + nums[start] + nums[end];

                differenceValue = Math.abs(sum - target);

                if (differenceValue < minimumDifference) {
                    minimumDifference = differenceValue;
                    result = sum;
                }

                if (sum - target == 0) {

                    return sum;

                }

                if (sum < target) {
                    start++;
                }

                if (sum > target) {
                    end--;
                }


            }

        }

        return result;
    }
}
