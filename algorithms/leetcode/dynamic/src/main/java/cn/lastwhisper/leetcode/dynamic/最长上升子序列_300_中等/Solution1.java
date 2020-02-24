package cn.lastwhisper.leetcode.dynamic.最长上升子序列_300_中等;

import java.util.Arrays;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/longest-increasing-subsequence/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：动态规划
     *  状态定义：dp[i]表示以num[i]为结尾的上升子序列
     *  转换方程：
     *      j∈[0,i]
     *      dp[i]=max(dp[j]+1,dp[i])
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int res = 1;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        // j∈[0,i)
        for (int i = 1; i < nums.length; i++) {
            // num[i]是否能做num[0,i)中某个数的结尾
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    // num[i]能以num[j]为结尾，更新dp[i]
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            // 记录最大系列长度
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        //int[] nums = {-2, -1};
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.err.println(new Solution1().lengthOfLIS(nums));
    }
}