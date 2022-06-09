package cn.cunchang.买卖股票的最佳时机II_122_简单;

import org.junit.Assert;

class Solution {
    /**
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * 贪心策略：从后往前遍历
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = prices.length - 1; i > 0; i--) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }

    public static void main(String[] args) {
//        int[] prices = {7, 1, 5, 3, 6, 4};
//        Assert.assertEquals(7, new Solution().maxProfit(prices));
        int[] prices = {1,2,3,4,5};
        Assert.assertEquals(4, new Solution().maxProfit(prices));
    }
}