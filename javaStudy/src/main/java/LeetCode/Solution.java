package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums，
 * 判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 */
public class Solution {

    public static void main(String[] args) {

        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        threeSum(nums);

    }
    public static  List<List<Integer>> threeSum(int[] nums) {

        for (int i = 1; i < nums.length; i++) {

            int j = i - 1;

            int insert = nums[i];

            while (j >= 0 && insert < nums[j]) {

                nums[j + 1] = nums[j];
                j--;
            }

            nums[j + 1] = insert;
        }
        int len = nums.length;
        List<List<Integer>> reLists = new ArrayList<List<Integer>>();
        for (int i = 0; i < nums.length-2; i++) {
            if(nums[i]>0 || nums[nums.length-1] <0){
                break;
            }
            if(((i>0)&&(nums[i-1]==nums[i]))){
                continue;
            }
            for (int j = i + 1; j < nums.length-1; j++) {
                int sum = nums[i] + nums[j];
                if(sum>0){
                    break;
                }
                if((j>i+1)&&nums[j-1]==nums[j]){
                    continue;
                }
                int start =j+1;
                int end = len-1;
                while(start<end){
                    int  middle = (start+(end-start)/2);
                    if(sum+nums[middle]<0){
                        start = middle;
                    }
                    if(sum+nums[middle]>0){
                        end = middle;
                    }
                    if(sum+nums[middle]==0){
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[middle]);
                        reLists.add(list);
                        break;
                    }
                }
            }
        }
        return reLists;
    }
}
