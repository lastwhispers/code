package cn.lastwhisper.leetcode.array.盛最多水的容器_11_中等;

import org.junit.Assert;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/container-with-most-water/
     * 编号：11
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：双指针（对撞指针）
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1, max = Integer.MIN_VALUE;
        while (l < r) {
            if (height[l] < height[r]) {
                max = Math.max(max, height[l] * (r - l));
                l++;
            } else {
                max = Math.max(max, height[r] * (r - l));
                r--;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Assert.assertEquals(49, new Solution2().maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }
}