package cn.lastwhisper.leetcode.dynamic.买卖股票的最佳时机_121_简单;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：暴力
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = prices.length - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                int profit = prices[i] - prices[j];
                if (profit > max) {
                    max = profit;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        //int[] nums = new int[]{7, 1, 5, 3, 6, 4};
        int[] nums = new int[]{7, 6, 4, 3, 1};
        System.out.println(new Solution1().maxProfit(nums));
    }
}