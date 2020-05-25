package cn.lastwhisper.leetcode.array.排序数组_912_中等.交换;

import org.junit.Assert;

class BubbleSort {
    /**
     * 题目地址：https://leetcode-cn.com/problems/rectangle-overlap/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     *  冒泡排序——稳定
     *  https://zh.wikipedia.org/wiki/%E5%86%92%E6%B3%A1%E6%8E%92%E5%BA%8F
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    public int[] sortArray(int[] nums) {
        // 标识数组是否无序
        boolean exchanged = true;
        for (int i = 0; exchanged && i < nums.length; i++) {
            exchanged = false;
            // 每次将最大值交换到无序数组最后的位置
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    exchanged = true;
                }
            }
        }
        return nums;
    }


    public static void main(String[] args) {
        Assert.assertArrayEquals(new int[]{1, 2, 3, 5}, new BubbleSort().sortArray(new int[]{5, 2, 3, 1}));
    }
}