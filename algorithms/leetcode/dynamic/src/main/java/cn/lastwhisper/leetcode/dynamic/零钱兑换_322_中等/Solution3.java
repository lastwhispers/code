package cn.lastwhisper.leetcode.dynamic.零钱兑换_322_中等;

import java.util.Arrays;

class Solution3 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/coin-change/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：动态规划
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        // 确定状态
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        //int[] coins = {2};
        //int amount = 3;
        System.err.println(new Solution3().coinChange(coins, amount));
    }
}