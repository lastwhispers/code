package cn.lastwhisper.leetcode.array.长度最小的子数组_209_中等;

import org.junit.Assert;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/minimum-size-subarray-sum/
     * 编号：209
     * -------------------------------------------------------------------
     * 思考：边界值一定要考虑取还是不取，为什么？
     * -------------------------------------------------------------------
     * 思路：二层循环暴力组合，一层循环计算组合范围值
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^3)
     * 空间复杂度：O(1)
     */
    public int minSubArrayLen(int s, int[] nums) {
        if (s <= 0 || nums == null) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        // 子数组长度有可能等于 nums.length
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int sum = 0;
                // 计算 [i,j] 子数组的和
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                }
                if (sum >= s) {
                    ans = Math.min(ans, j - i + 1);// i=0,j=1 ————> ans=2
                    j = nums.length;// 退出内循环
                }
            }
        }
        // 子数组长度等于 nums.length+1，说明无解
        if (ans == Integer.MAX_VALUE)
            return 0;
        return ans;
    }

    public static void main(String[] args) {
        Assert.assertEquals(2, new Solution1().minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }
}