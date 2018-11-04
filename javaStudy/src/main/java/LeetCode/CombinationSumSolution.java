package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 * 说明：所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 */
public class CombinationSumSolution {

    public static void main(String[] args) {

        int[] candidates = {1};

        new CombinationSumSolution().combinationSum(candidates, 2);

    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> lists = new ArrayList<>();

        if (candidates.length == 0) {
            return lists;
        }

        //希尔排序
        int d = candidates.length;

        while (true) {

            d = d / 2;

            for (int i = 0; i < d; i++) {

                for (int k = i + d; k < candidates.length; k = k + d) {

                    int temp = candidates[k];
                    int j;
                    for (j = k - d; j >= 0 && candidates[j] > temp; j = j - d) {

                        candidates[j + d] = candidates[j];

                    }

                    candidates[j + d] = temp;
                }

            }

            if (d == 0 || d == 1) {
                break;
            }

        }

        if (candidates[0] > target) {

            return lists;

        }

        List<Integer> list = new ArrayList<>();

        recursion(lists, 0, candidates, target, list);

        System.out.println(Arrays.toString(lists.toArray()));

        return lists;
    }

    public void recursion(List<List<Integer>> lists, int index, int[] candidates, int target, List<Integer> list) {

        for (int i = index; i < candidates.length; i++) {

            if (target == candidates[i]) {

                List<Integer> list1 = new ArrayList<>();

                list1.addAll(list);

                list1.add(candidates[i]);

                lists.add(list1);

            }

            if (target > candidates[i]) {

                List<Integer> list1 = new ArrayList<>();

                list1.addAll(list);

                list1.add(candidates[i]);

                recursion(lists, i, candidates, target-candidates[i], list1);
            }

            if (target < candidates[i]) {
                break;
            }


        }
    }

}
