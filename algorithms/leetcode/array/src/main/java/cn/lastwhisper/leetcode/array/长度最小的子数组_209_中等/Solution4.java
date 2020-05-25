package cn.lastwhisper.leetcode.array.长度最小的子数组_209_中等;

import org.junit.Assert;

class Solution4 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/minimum-size-subarray-sum/
     * 编号：209
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：滑动窗口
     * -------------------------------------------------------------------
     * 时间复杂度：O(n) // l 和 r 指针同时遍历了一遍数组 O(2n)
     * 空间复杂度：O(1)
     */
    public int minSubArrayLen(int s, int[] nums) {
        int l = 0, sum = 0, ans = Integer.MAX_VALUE;
        for (int r = 0; r < nums.length; r++) {
            sum += nums[r];
            while (sum >= s) {
                ans = Math.min(ans, r - l + 1);
                sum -= nums[l++];
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public static void main(String[] args) {
        Assert.assertEquals(2, new Solution4().minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }
}