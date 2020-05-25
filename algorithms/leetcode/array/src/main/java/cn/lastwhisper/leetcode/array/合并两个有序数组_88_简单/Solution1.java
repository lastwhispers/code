package cn.lastwhisper.leetcode.array.合并两个有序数组_88_简单;

import org.junit.Assert;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/merge-sorted-array/
     * 编号：88
     * -------------------------------------------------------------------
     * 思考：
     *  数据特征：数组有序
     * -------------------------------------------------------------------
     * 思路：
     *   归并排序，归的过程。
     *  （1）从前到后比较(找小)复制只用 nums1，需要移动 nums1 中的元素，不如创建中间数据
     *  （2）从后到前比较(找大)复制只用 nums1，无需移动 nums1 中的元素
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 暂存 nums1 和 nums2 的数据
        int[] temp = new int[m + n];
        int p1 = 0, p2 = 0, t = 0;
        // 将 nums1 和 nums2 有序复制到 temp，直到某个数组复制完
        while (p1 < m && p2 < n) {
            if (nums1[p1] > nums2[p2]) {
                temp[t] = nums2[p2++];
            } else {
                temp[t] = nums1[p1++];
            }
            t++;
        }
        // 将 nums1 剩余元素复制到 temp
        while (p1 < m) {
            temp[t++] = nums1[p1++];
        }
        // 将 nums2 剩余元素复制到 temp
        while (p2 < n) {
            temp[t++] = nums2[p2++];
        }
        // 将 temp 所有元素复制到 nums1
        System.arraycopy(temp, 0, nums1, 0, temp.length);
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0}, nums2 = {2, 5, 6};
        int m = 3, n = 3;
        new Solution1().merge(nums1, m, nums2, n);

        Assert.assertArrayEquals(new int[]{1, 2, 2, 3, 5, 6}, nums1);
    }
}