package cn.lastwhisper.leetcode.dynamic.买卖股票的最佳时机_121_简单;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：动态规划
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        // 前i天的最大收益 = max{前i-1天的最大收益，第i天的价格-前i-1天中的最小价格}
        // min是前i-1天中的最小价格，前i-1天的最大收益
        int min = prices[0], max = 0;
        for (int i = 1; i < prices.length; i++) {
            max = Math.max(max, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{7, 1, 5, 3, 6, 4};
        //int[] nums = new int[]{7, 6, 4, 3, 1};
        System.out.println(new Solution2().maxProfit(nums));
    }
}