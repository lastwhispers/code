package cn.lastwhisper.offer.面试题59_I_滑动窗口的最大值;

import java.util.Arrays;

class Solution {
    /**
     * 题目地址：https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/
     * 编号：面试题59 - I
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：双指针（滑动窗口）
     *  （1）
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k)
            return nums;




        return null;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(Arrays.toString(new Solution().maxSlidingWindow(nums, k)));
    }
}