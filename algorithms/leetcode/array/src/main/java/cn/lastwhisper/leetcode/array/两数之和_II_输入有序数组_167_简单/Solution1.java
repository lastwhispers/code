package cn.lastwhisper.leetcode.array.两数之和_II_输入有序数组_167_简单;

import org.junit.Assert;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
     * 编号：167
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：暴力搜索，找出所有组合进行对比
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) {
            throw new IllegalArgumentException("Illegal argument numbers");
        }
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {// i + 1 是一个优化点
                if (i != j && numbers[i] + numbers[j] == target) {
                    return new int[]{i + 1, j + 1};
                }
            }
        }
        throw new IllegalStateException("No results found");
    }

    public static void main(String[] args) {
        Assert.assertArrayEquals(new int[]{1, 2}, new Solution1().twoSum(new int[]{2, 7, 11, 15}, 9));
    }
}