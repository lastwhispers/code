package cn.lastwhisper.leetcode.array.长度最小的子数组_209_中等;

import org.junit.Assert;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/minimum-size-subarray-sum/
     * 编号：209
     * -------------------------------------------------------------------
     * 思考：边界值一定要考虑取还是不取，为什么？
     * -------------------------------------------------------------------
     * 思路：二层循环暴力组合
     *  组合的和提前计算
     *  （1）提前将 sum[0...n] 计算出来， sum[0] = 0; sum[i] = sum[i-1] + num[i-1]
     *  （2）nums[i...j] 的和 = sum[j+1]-sum[i]
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     */
    public int minSubArrayLen(int s, int[] nums) {
        if (s <= 0 || nums == null) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        // nums=>  2, 3, 1, 2, 4, 3
        // sum=>0, 2, 5, 6, 8, 12, 15
        // sums[i] 存放 nums[0...i-1] 的和
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        // 子数组长度有可能等于 nums.length
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                // sum[j+1] - sum[0] = num[0,j]的和，sum[0]==0
                // sum[j+1] - sum[i] = num[i,j]的和
                if (sum[j + 1] - sum[i] >= s) {
                    ans = Math.min(ans, j - i + 1);
                    //j = nums.length;// 退出内循环
                    break;
                }
            }
        }
        // 子数组长度等于 nums.length+1，说明无解
        if (ans == Integer.MAX_VALUE)
            return 0;
        return ans;
    }

    public static void main(String[] args) {
        Assert.assertEquals(2, new Solution2().minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }
}