package cn.lastwhisper.leetcode.hashtable.三数之和_15_中等;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution1 {
    /**
     * 没做出来，暂时保留
     *
     * 题目地址：https://leetcode-cn.com/problems/3sum/
     * -------------------------------------------------------------------
     * 思考：
     *
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null) throw new IllegalArgumentException("非法参数");
        Arrays.sort(nums);
        // 结果
        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 对i去重
            }
            for (int j = i + 1; j < nums.length - 1; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue; // 对j去重
                }
                for (int k = j + 1; k < nums.length; k++) {
                    if (k > j + 1 && nums[k] == nums[k - 1]) {
                        continue; // 对k去重
                    }
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        resultList.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }

        return resultList;
    }

    public static void main(String[] args) {
        //new Solution1().threeSum(new int[]{-1, 0, 1, 2, -1, -4}).forEach((list) -> {
        //    System.out.println();
        //    list.forEach(System.out::print);
        //});

        // error example
        new Solution1().threeSum(new int[]{0, 0, 0, 0}).forEach((list) -> {
            System.out.println();
            list.forEach(System.out::print);
        });

    }
}