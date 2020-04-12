package cn.lastwhisper.leetcode.array.排序.排序数组_912_中等.插入;

import org.junit.Assert;

class ShellSort {
    /**
     * 题目地址：https://leetcode-cn.com/problems/rectangle-overlap/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     *  希尔排序——不稳定
     *  https://zh.wikipedia.org/wiki/%E6%8F%92%E5%85%A5%E6%8E%92%E5%BA%8F
     * -------------------------------------------------------------------
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(1)
     */
    public int[] sortArray(int[] nums) {
        int gap = nums.length / 2;
        int j;
        int temp;
        while (gap > 0) {
            // 对每个数组进行插入排序；由于每个数组并不是真的存在的数组，而是一个大数组
            // 所以通过gap进行取值
            for (int i = gap; i < nums.length; i++) {
                j = i; // 缓存每个数组的起始下标
                temp = nums[i]; // 缓存每个数组的起始下标的数值
                if (temp < nums[j - gap]) {
                    while (j - gap >= 0 && temp < nums[j - gap]) {
                        // 从前往后移动
                        nums[j] = nums[j - gap];
                        j = j - gap;
                    }
                    nums[j] = temp;
                }
            }
            gap = gap / 2;
        }
        return nums;
    }


    public static void main(String[] args) {
        Assert.assertArrayEquals(new int[]{1, 2, 3, 5}, new ShellSort().sortArray(new int[]{5, 2, 3, 1}));
    }
}