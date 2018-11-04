package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个数组 candidates 和一个目标数 target ，
 * 找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 * 说明：
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 */
public class CombinationSumSolutionTwo {

    public static void main(String[] args) {

        int[] candidates = {10,1,2,7,6,1,5};

        int target = 8;

        new CombinationSumSolutionTwo().combinationSum2(candidates, target);

    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        List<List<Integer>> lists = new ArrayList<>();

        //先用增强冒泡排序
        boolean flag = true;

        int k = candidates.length;

        while (flag) {

            flag = false;

            for (int i = 1; i < k; i++) {

                if (candidates[i-1] > candidates[i]) {

                    flag = true;

                    int temp = candidates[i];

                    candidates[i] = candidates[i-1];

                    candidates[i-1] = temp;
                }

            }
            k--;
        }

        System.out.println(Arrays.toString(candidates));

        List<Integer> list = new ArrayList<>();

        recursion(lists, candidates, list, 0, target);

        System.out.println(Arrays.toString(lists.toArray()));

        return lists;
    }

    public void recursion(List<List<Integer>> lists, int[] candidates, List<Integer> list, int index, int target) {

        for (int i = index; i < candidates.length; i++) {

            if (i > index && candidates[i] == candidates[i-1]) {
                continue;
            }

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

                recursion(lists, candidates, list1, i+1, target - candidates[i]);
            }

            if (target < candidates[i]) {
                break;
            }
        }
        
    }
}










































