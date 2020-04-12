package cn.lastwhisper.leetcode.array.排序.排序数组_912_中等.选择;

import org.junit.Assert;

class SelectSort {
    /**
     * 题目地址：https://leetcode-cn.com/problems/rectangle-overlap/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     *  选择排序——不稳定
     *  https://zh.wikipedia.org/wiki/%E9%80%89%E6%8B%A9%E6%8E%92%E5%BA%8F
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    public int[] sortArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {

            int minIdx = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIdx]) {
                    minIdx = j;
                }
            }
            // 每次找到无序数组最小值的下标，放到已排序序列的末尾
            if (nums[i] > nums[minIdx]) {
                int temp = nums[i];
                nums[i] = nums[minIdx];
                nums[minIdx] = temp;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        Assert.assertArrayEquals(new int[]{1, 2, 3, 5}, new SelectSort().sortArray(new int[]{5, 2, 3, 1}));
        Assert.assertArrayEquals(new int[]{0, 0, 1, 1, 2, 5}, new SelectSort().sortArray(new int[]{5, 1, 1, 2, 0, 0}));
    }
}