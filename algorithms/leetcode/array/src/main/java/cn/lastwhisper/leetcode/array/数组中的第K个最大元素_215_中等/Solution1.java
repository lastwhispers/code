package cn.lastwhisper.leetcode.array.数组中的第K个最大元素_215_中等;

import org.junit.Assert;

import java.util.Arrays;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
     * 编号：215
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     *   升序排序，取n-k个元素
     * -------------------------------------------------------------------
     * 时间复杂度：O(n*logn)
     * 空间复杂度：O(1)
     */
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public static void main(String[] args) {
        Assert.assertEquals(5, new Solution1().findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
    }
}