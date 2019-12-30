package cn.lastwhisper.leetcode.hashtable.三数之和_15_Middle;

import java.util.*;

class Solution1 {
    /**
     * https://leetcode-cn.com/problems/3sum/
     * -------------------------------------------------------------------
     * 思路：排序+双索引+hash表
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null) throw new IllegalArgumentException("非法参数");
        // 数组排序
        Arrays.sort(nums);
        // hash表
        Map<Integer, Integer> map = new HashMap<>();
        // 结果
        List<List<Integer>> list = new ArrayList<>();
        // [l,r]
        int l = 0, r = nums.length-1;
        int k = nums[l];
        while (l<r){

        }

        return list;
    }

    public static void main(String[] args) {
        new Solution1().threeSum(new int[]{-1, 0, 1, 2, -1, -4}).forEach((list) -> {
            System.out.println();
            list.forEach(System.out::print);
        });

    }
}