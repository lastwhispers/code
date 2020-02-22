package cn.lastwhisper.leetcode.dynamic.零钱兑换_322_中等;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/coin-change/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：递归dfs-暴力搜索-备忘录
     *  先找到子问题
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^k*k)，n^k个结点
     * 空间复杂度：O()
     */
    public int coinChange(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        return tryCoinChange(coins, amount, memo);
    }

    public int tryCoinChange(int[] coins, int amount, int[] memo) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        if (memo[amount] != 0) {
            return memo[amount];
        }
        int freq = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subFreq = tryCoinChange(coins, amount - coin, memo);
            // 子问题无解，不更新freq
            if (subFreq == -1) {
                continue;
            }
            freq = Math.min(freq, 1 + subFreq);
        }
        // 所有子问题都无解，返回-1
        memo[amount] = freq == Integer.MAX_VALUE ? -1 : freq;
        return memo[amount];
    }

    public static void main(String[] args) {
        //int[] coins = {1, 2, 5};
        //int amount = 11;
        int[] coins = {2};
        int amount = 3;
        System.err.println(new Solution2().coinChange(coins, amount));
    }
}