package LeetCode;

import java.util.Arrays;

/**
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。画 n 条垂直线，
 * 使得垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，
 * 使得它们与 x 轴共同构成的容器可以容纳最多的水。
 */
public class MaxWaterSolution {

    public static void main(String[] args) {

        int[] height = {3,2,1,3};

        System.out.println(new MaxWaterSolution().maxArea(height));

    }

    public int maxArea(int[] height) {

        int result = 0;

        if (height.length < 2) {

            return 0;

        }

        int i = 0;
        int j = height.length - 1;

        while (i < j) {

            int sum = (j-i)*(height[i] < height[j] ?height[i]:height[j]);

            if (sum > result) {
                result = sum;
            }

            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }

        return result;
    }

}




















