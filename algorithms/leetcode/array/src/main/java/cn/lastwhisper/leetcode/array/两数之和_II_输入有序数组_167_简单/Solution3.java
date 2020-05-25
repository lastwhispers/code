package cn.lastwhisper.leetcode.array.两数之和_II_输入有序数组_167_简单;

import org.junit.Assert;

class Solution3 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
     * 编号：167
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：双指针（对撞指针）
     *  这种思路的前提是数组有序
     *  （1）定义两个初始指针 l,r 分别指向数组头尾。
     *  （2）nums[l]+nums[r] > target，r--
     *  （3）nums[l]+nums[r] < target，l++
     *  （4）nums[l]+nums[r] == target，找到答案
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) {
            throw new IllegalArgumentException("Illegal argument numbers");
        }
        int l = 0, r = numbers.length - 1;
        // 这里不能等于，因为 index1 必须小于 index2
        while (l < r) {
            if (numbers[l] + numbers[r] == target) {
                return new int[]{l + 1, r + 1};
            } else if (numbers[l] + numbers[r] < target) {
                l++;
            } else {
                r--;
            }
        }
        throw new IllegalStateException("No results found");
    }

    public static void main(String[] args) {
        Assert.assertArrayEquals(new int[]{1, 3}, new Solution3().twoSum(new int[]{2, 3, 4}, 6));
    }
}