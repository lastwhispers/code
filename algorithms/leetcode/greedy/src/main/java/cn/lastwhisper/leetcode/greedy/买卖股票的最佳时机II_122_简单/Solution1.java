package cn.lastwhisper.leetcode.greedy.买卖股票的最佳时机II_122_简单;

class Solution1 {

    /**
     * 题目地址：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：暴力搜索
     *  1、买入之后：1.不操作 2.卖出
     *  2、不操作之后：1.不操作 2.买入
     *  3、卖出之后：1.不操作 2.买入
     *  4、初始：1.不操作 2.买入
     *
     *  共有两种状态：
     *      1 持股
     *      2、3、4 未持股
     *
     * -------------------------------------------------------------------
     * 时间复杂度：O(2^n)
     * 空间复杂度：O(1)
     */
    public int maxProfit(int[] prices) {
        tryProfit(prices, 0, false, 0);
        return profit;
    }

    int profit = 0;

    /**
     * @param prices 股价数组
     * @param index 第几天
     * @param status true持股，false不持股
     * @param profit 当前收益
     */
    private void tryProfit(int[] prices, int index, boolean status, int profit) {
        if (index == prices.length ) {
            this.profit = Math.max(this.profit, profit);
            return;
        }
        // 不卖也不买
        tryProfit(prices, index + 1, status, profit);
        if (status) {
            // 尝试卖出
            tryProfit(prices, index + 1, false, profit + prices[index]);
        } else {
            // 尝试买入
            tryProfit(prices, index + 1, true, profit - prices[index]);
        }
    }


    public static void main(String[] args) {
        //int[] prices = {7, 1, 5, 3, 6, 4};
        int[] prices = {1, 2, 3, 4, 5};
        System.err.println(new Solution1().maxProfit(prices));
    }
}