package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSolution {

    public static void main(String[] args) {
        int[] nums = new int[]{6,-5,-6,-1,-2,8,-1,4,-10,-8,-10,-2,-4,-1,-8,-2,8,9,-5,-2,-8,-9,-3,-5}; //{-4, -1, -1, 0, 1, 2}
        System.out.println(threeSum(nums));
    }

    public static  List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> lists = new ArrayList<>();

        if (nums.length <3) {
            return lists;
        }

        //先希尔排序
        int d = nums.length;
        while (true) {
            d = d/2;
            for (int i = 0;i<d;i++) {
                for (int  j=i+d;j < nums.length;j+=d) {
                    int temp = nums[j];
                    int k;
                    for (k = j-d;k>=0 && nums[k] > temp;k=k-d) {
                        nums[k+d] = nums[k];
                    }
                    nums[k+d] = temp;
                }
            }
            if (d == 1) {
                break;
            }
        }

        int start1 =0;

        int end1 = nums.length;

        int  middle1=0;

        while(start1<end1){
            middle1 = (start1+(end1-start1)/2);

            if(start1==middle1||end1==middle1){
                break;
            }
            if(middle1>0&&nums[middle1]>=0&&nums[middle1-1]<0){
                break;
            }
            if (nums[middle1]>=0){
                end1=middle1;
            }else {
                start1=middle1;
            }

        }
        System.out.println(nums[middle1]);

        for (int i = 0;i < nums.length-2;i++) {

            if (nums[0] > 0 || nums[nums.length-1] < 0) {
                break;
            }

            if ((i > 0) && (nums[i] == nums[i-1])) {
                continue;
            }

            for (int j = i+1;j < nums.length-1;j++) {

                if ((j > i+1) && (nums[j] == nums[j-1])) {
                    continue;
                }

                int start = j+1;

                int end = nums.length-1;

                while (true) {

                    if (nums[i] + nums[j] + nums[start] == 0 ) {

                        List<Integer> list = new ArrayList<>();

                        list.add(nums[i]);

                        list.add(nums[j]);

                        list.add(nums[start]);

                        lists.add(list);

                        break;
                    }

                    if (nums[i] + nums[j] + nums[end] == 0) {

                        List<Integer> list = new ArrayList<>();

                        list.add(nums[i]);

                        list.add(nums[j]);

                        list.add(nums[end]);


                        lists.add(list);

                        break;
                    }

                    int len = start + ((end-start) >> 2);

                    if (len == start || len == end) {

                        for (int z = start;z<=end;z++) {

                            if (nums[i] + nums[j] + nums[z] == 0) {
                                List<Integer> list = new ArrayList<>();

                                list.add(nums[i]);

                                list.add(nums[j]);

                                list.add(nums[z]);

                                lists.add(list);

                                break;
                            }

                        }
                        break;
                    }


                    if (nums[i] + nums[j] + nums[len] > 0) {

                        end = len;

                    } else if(nums[i] + nums[j] + nums[len]  < 0) {

                        start = len;

                    } else {

                        List<Integer> list = new ArrayList<>();

                        list.add(nums[i]);

                        list.add(nums[j]);

                        list.add(nums[len]);

                        lists.add(list);

                        break;
                    }

                }

            }

        }

        return lists;

    }
}
