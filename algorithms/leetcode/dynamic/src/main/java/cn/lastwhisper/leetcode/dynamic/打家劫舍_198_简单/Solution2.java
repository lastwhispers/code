package cn.lastwhisper.leetcode.dynamic.打家劫舍_198_简单;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/house-robber/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：动态规划
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        // memo[i] 表示考虑抢劫 nums[i...n-1] 所能获得最大收益（不是说一定从 i 开始抢劫）
        int[] memo = new int[n];
        // 先考虑最简单的情况
        memo[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            // memo[i] 的取值在考虑抢劫 i 号房子和不考虑抢劫之间取最大值
            for (int j = i; j < n; j++) {
                memo[i] = Math.max(memo[i], nums[j] + (j + 2 < n ? memo[j + 2] : 0));
            }
        }

        return memo[0];
    }


    public static void main(String[] args) {
        System.out.println(new Solution2().rob(new int[]{2, 1, 1, 2}));
        System.out.println(new Solution2().rob(new int[]{2, 7, 9, 3, 1}));
        System.out.println(new Solution2().rob(new int[]{1, 2, 3, 1}));
    }
}