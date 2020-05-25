package cn.lastwhisper.leetcode.array.合并两个有序数组_88_简单;

import org.junit.Assert;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/merge-sorted-array/
     * 编号：88
     * -------------------------------------------------------------------
     * 思考：
     *  数据特征：数组有序
     * -------------------------------------------------------------------
     * 思路：
     *  从后向前复制
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1, total = m + n - 1;
        // 将 num1 和 num2 中最大的复制到 nums1的最后面
        while (p1 >= 0 && p2 >= 0) {
            nums1[total--] = nums1[p1] > nums2[p2] ? nums1[p1--] : nums2[p2--];
        }
        // 如果 num1 还没复制完，不用管，因为num1自身就是有序的
        // 如果 num2 还没复制完，把剩余复制进去
        while (p2 >= 0) {
            nums1[total--] = nums2[p2--];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0}, nums2 = {2, 5, 6};
        int m = 3, n = 3;
        new Solution2().merge(nums1, m, nums2, n);

        Assert.assertArrayEquals(new int[]{1, 2, 2, 3, 5, 6}, nums1);
    }
}