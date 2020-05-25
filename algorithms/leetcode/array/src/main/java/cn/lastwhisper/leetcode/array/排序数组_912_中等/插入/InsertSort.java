package cn.lastwhisper.leetcode.array.排序数组_912_中等.插入;

import org.junit.Assert;

class InsertSort {
    /**
     * 题目地址：https://leetcode-cn.com/problems/rectangle-overlap/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     *  插入排序——稳定
     *  https://zh.wikipedia.org/wiki/%E6%8F%92%E5%85%A5%E6%8E%92%E5%BA%8F
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    public int[] sortArray(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];// 无序数组第一个值，待插入数据
            int idx = i - 1;// 有序数组最后一个值下标
            // 把待插入的位置腾出来
            while (idx >= 0 && nums[idx] > num) {
                nums[idx + 1] = nums[idx];
                idx--;
            }
            nums[idx + 1] = num;
        }
        return nums;
    }


    public static void main(String[] args) {
        Assert.assertArrayEquals(new int[]{1, 2, 3, 5}, new InsertSort().sortArray(new int[]{5, 2, 3, 1}));
    }
}