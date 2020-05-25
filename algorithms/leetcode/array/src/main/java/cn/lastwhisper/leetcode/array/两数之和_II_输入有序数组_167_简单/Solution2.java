package cn.lastwhisper.leetcode.array.两数之和_II_输入有序数组_167_简单;

import org.junit.Assert;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
     * 编号：167
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：二分搜索
     *  （1）遍历数组，对每一个值 nums[i]，在 nums 数组中进行 [i,n] 范围的二分搜索
     * -------------------------------------------------------------------
     * 时间复杂度：O(n*logn)
     * 空间复杂度：O(1)
     */
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) {
            throw new IllegalArgumentException("Illegal argument numbers");
        }
        for (int i = 0; i < numbers.length; i++) {
            int j = bs(numbers, i, numbers.length - 1, target - numbers[i]);
            if (j != -1) {
                return new int[]{i + 1, j + 1};
            }
        }
        throw new IllegalStateException("No results found");
    }

    /**
     * @param nums 待搜索数组
     * @param l  起始索引
     * @param r  终止索引
     * @param target 目标数值
     * @return int 目标数值在数组中的索引
     */
    private int bs(int[] nums, int l, int r, int target) {
        // 这里是“<=”因为 [l,r] 内所有值都是可能的目标
        while (l <= r) {
            int mid = (l - r) / 2 + r;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        //Assert.assertArrayEquals(new int[]{1, 2}, new Solution2().twoSum(new int[]{2, 7, 11, 15}, 9));
        Assert.assertArrayEquals(new int[]{1, 3}, new Solution2().twoSum(new int[]{2, 3, 4}, 6));
    }
}