package cn.lastwhisper.leetcode.dynamic.打家劫舍_198_简单;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/house-robber/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：动态规划
     *  1、状态定义：dp[i]表示偷到至第i个房子，所获取最大的利益
     *  2、状态分析
     *      初始：
     *          dp[0] = nums[0];
     *          dp[1] = Math.max(nums[0], nums[1])
     *      方程：
     *          dp[i]=max(dp[i-2]+num[i],dp[i-1])
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     */
    public int rob(int[] nums) {
        // 特判
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        int[] array = new int[]{2, 1, 1, 2};
        //int[] array = new int[]{2, 7, 9, 3, 1};
        //int[] array = new int[]{1, 2, 3, 1};
        System.out.println(new Solution1().rob(array));
    }
}