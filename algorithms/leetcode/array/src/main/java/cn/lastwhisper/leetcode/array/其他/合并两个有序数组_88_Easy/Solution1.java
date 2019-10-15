package cn.lastwhisper.leetcode.array.合并两个有序数组_88_Easy;

import java.util.Arrays;

class Solution1 {
    /**
     * https://leetcode-cn.com/problems/merge-sorted-array/
     * 合并两个有序数组
     *  核心思路：归并排序，并的算法思路
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null|| nums1.length == 0  || nums2 == null ) {
            return;
        }
        int[] temp = new int[m + n];
        int p0 = 0, p1 = 0, index = 0;
        while (p0 < m && p1 < n) {
            int num1 = nums1[p0];
            int num2 = nums2[p1];
            if (num1 <= num2) {
                temp[index++] = num1;
                p0++;
            } else {
                temp[index++] = num2;
                p1++;
            }
        }
        while (p0 < m) {
            temp[index++] = nums1[p0];
            p0++;
        }
        while (p1 < n) {
            temp[index++] = nums2[p1];
            p1++;
        }

        for (int i = 0; i < temp.length; i++) {
            nums1[i] = temp[i];
        }

    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        new Solution1().merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }

}