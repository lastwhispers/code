package cn.lastwhisper.leetcode.dynamic.零钱兑换_322_中等;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/coin-change/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：递归dfs-暴力搜索
     *  先找到子问题
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^k*k)，n^k个结点
     * 空间复杂度：O(n^k)
     */
    public int coinChange(int[] coins, int amount) {
        return tryCoinChange(coins, amount);
    }

    public int tryCoinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        int freq = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subFreq = tryCoinChange(coins, amount - coin);
            if (subFreq == -1) {
                continue;
            }
            freq = Math.min(freq, 1 + subFreq);
        }

        return freq == Integer.MAX_VALUE ? -1 : freq;
    }

    public static void main(String[] args) {
        //int[] coins = {1, 2, 5};
        //int amount = 11;
        int[] coins = {2};
        int amount = 3;
        System.err.println(new Solution1().coinChange(coins, amount));
    }
}