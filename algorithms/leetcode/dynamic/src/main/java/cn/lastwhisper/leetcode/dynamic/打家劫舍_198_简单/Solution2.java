package cn.lastwhisper.leetcode.dynamic.打家劫舍_198_简单;

import java.util.Arrays;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/house-robber/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：递归+备忘录
     *  状态：考虑抢劫 nums[index...num.length） 这个范围内的所有房子
     *  状态转移：tryRob(n) = Max{rob(0) + tryRob(2), rob(1) + tryRob(3)... rob(n-3) + tryRob(n-1), rob(n-2), rob(n-1)}
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int rob(int[] nums) {
        int[] memo = new int[nums.length];
        // 必须先初始化为-1，有特殊用例
        Arrays.fill(memo, -1);
        return tryRob(0, nums, memo);
    }

    public int tryRob(int index, int[] nums, int[] memo) {
        if (index >= nums.length) {
            return 0;
        }
        if (memo[index] != -1) {
            return memo[index];
        }
        int max = 0;
        for (int i = index; i < nums.length; i++) {
            max = Math.max(max, nums[i] + tryRob(i + 2, nums, memo));
        }
        memo[index] = max;
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Solution1().rob(new int[]{2, 1, 1, 2}));
        System.out.println(new Solution1().rob(new int[]{2, 7, 9, 3, 1}));
        System.out.println(new Solution2().rob(new int[]{1, 2, 3, 1}));
    }
}