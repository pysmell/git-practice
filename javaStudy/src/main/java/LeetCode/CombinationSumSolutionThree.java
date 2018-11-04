package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 找出所有相加之和为 n 的 k 个数的组合。
 * 组合中只允许含有1 - 9的正整数，并且每种组合中不存在重复的数字
 * 说明：
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 */
public class CombinationSumSolutionThree {

    public static void main(String[] args) {
        new CombinationSumSolutionThree().combinationSum3(3, 6);
    }

    public List<List<Integer>> combinationSum3(int k, int n) {

        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        List<List<Integer>> lists = new ArrayList<>();

        List<Integer> list = new ArrayList<>();

        recursion(lists, list, n, 0, array, k);

        System.out.println(Arrays.toString(lists.toArray()));

        return lists;
    }

    public void recursion(List<List<Integer>> lists, List<Integer> list, int target, int index, int[] array, int k) {

        for (int i = index; i < array.length; i++) {

            if (target == array[i] && list.size() == k-1) {

                List<Integer> list1  = new ArrayList<>();

                list1.addAll(list);

                list1.add(array[i]);

                lists.add(list1);

            } else if(target > array[i]) {

                List<Integer> list1  = new ArrayList<>();

                list1.addAll(list);

                list1.add(array[i]);

                recursion(lists, list1, target - array[i], i+1, array, k);
            } else {
                break;
            }

        }

    }

}
