package cn.lastwhisper.leetcode.array.盛最多水的容器_11_中等;

import org.junit.Assert;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/container-with-most-water/
     * 编号：11
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：暴力组合高和底
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    public int maxArea(int[] height) {
        int max = Integer.MIN_VALUE, h;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                h = Math.min(height[i], height[j]);// 最小高度
                max = Math.max(max, h * (j - i));
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Assert.assertEquals(49, new Solution1().maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }
}