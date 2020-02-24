package cn.lastwhisper.leetcode.greedy.买卖股票的最佳时机II_122_简单;

class Solution4 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：贪心算法
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int maxProfit(int[] prices) {
        int profit = 0, temp;
        for (int i = prices.length - 1; i > 0; i--) {
            temp = prices[i] - prices[i - 1];
            if (temp > 0) {
                profit += temp;
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        //int[] prices = {7, 1, 5, 3, 6, 4};
        int[] prices = {1, 2, 3, 4, 5};
        System.err.println(new Solution4().maxProfit(prices));
    }
}