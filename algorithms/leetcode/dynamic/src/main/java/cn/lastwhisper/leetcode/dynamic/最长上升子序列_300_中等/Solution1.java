package cn.lastwhisper.leetcode.dynamic.最长上升子序列_300_中等;

import java.util.Arrays;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/longest-increasing-subsequence/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：动态规划
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        int res = 1;
        // 以i为结尾的lis
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            res = Math.max(res, dp[i]);
        }

        return res;
    }

    public static void main(String[] args) {
        //int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int[] nums = {-2, -1};
        System.err.println(new Solution1().lengthOfLIS(nums));
    }
}