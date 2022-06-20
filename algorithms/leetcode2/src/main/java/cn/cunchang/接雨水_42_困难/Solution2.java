package cn.cunchang.接雨水_42_困难;

import org.junit.Assert;

class Solution2 {
    /**
     * 题目地址：https://leetcode.cn/problems/container-with-most-water/
     * -------------------------------------------------------------------
     * 思考：只看局部柱子最多盛放水量
     * -------------------------------------------------------------------
     * 思路：基于solution1，精简代码
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    public int trap(int[] height) {
        int count = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int leftMax = 0, rightMax = 0;
            // 左边最高，j <= i包含自己，后面不用判断小于的情况
            for (int j = 0; j <= i; j++) {
                leftMax = Math.max(leftMax, height[j]);
            }
            // 右边最高，j >= i 包含自己
            for (int j = height.length - 1; j >= i; j--) {
                rightMax = Math.max(rightMax, height[j]);
            }
            count += Math.min(leftMax, rightMax) - height[i];
        }
        return count;
    }

    public static void main(String[] args) {
//        Assert.assertEquals(2, new Solution1().maxDistance(new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}}));
        Solution2 solution2 = new Solution2();
        Assert.assertEquals(6, solution2.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));

    }
}