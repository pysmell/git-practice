package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个没有重复数字的序列，返回其所有可能的全排列
 * 输入: [1,2,3,4]
 * 输出:
 * [
 * [1,2,3,4],
 * [1,2,4,3],
 * [1,3,2,4]
 * [1,3,4,2]
 * [1,4,2,3]
 * [1,4,3,2]
 * [2,1,3,4],
 * [2,1,4,3],
 * [2,3,4,1]
 * [2,3,1,4]
 * [2,4,1,3]
 * [2,4,3,1]
 * [3,1,2,4],
 * [3,1,4,2],
 * [3,2,1,4]
 * [3,2,4,1]
 * [3,4,1,2]
 * [3,4,2,1]
 * [4,1,2,3]
 * [4,1,3,2]
 * [4,2,1,3]
 * [4,2,3,1]
 * [4,3,1,2]
 * [4,3,2,1]
 * ]
 */
public class PermuteSolution {

    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> lists = new ArrayList<>();

        int index = 0;

        List<Integer> list = new ArrayList<>();

        recursion(lists, index, nums, list);

        return lists;
    }

    public void recursion(List<List<Integer>> lists, int index, int[] nums, List<Integer> list) {

        for (int i = index; i < nums.length; i++) {



        }

    }
}
