package cn.lastwhisper.leetcode.array.其他.多数元素_169_简单;

import org.junit.Assert;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/majority-element/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：摩尔投票法
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int majorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += candidate == num ? 1 : -1;
        }
        return candidate;
    }

    public static void main(String[] args) {
        Assert.assertEquals(3, new Solution2().majorityElement(new int[]{3, 2, 3}));
        Assert.assertEquals(2, new Solution2().majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));
    }
}