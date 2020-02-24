package cn.lastwhisper.leetcode.dynamic.最大子序和_53_简单;

class Solution3 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/maximum-subarray/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：动态规划
     *  状态：dp[i]表示以num[i]为结尾的连续子序列的最大和
     *  状态转换方程：dp[i] = Math.max(dp[i - 1] + nums[i], nums[i])
     *
     *  优化空间：只需要两个变量就可以完成动态规划
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int maxSubArray(int[] nums) {
        // 初始值
        int res = nums[0];
        int prev = nums[0], current;
        for (int i = 1; i < nums.length; i++) {
            current = Math.max(prev + nums[i], nums[i]);
            res = Math.max(res, current);
            prev = current;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.err.println(new Solution3().maxSubArray(nums));
    }
}