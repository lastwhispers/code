package cn.lastwhisper.leetcode.array.合并两个有序数组_88_Easy;

import java.util.Arrays;

class Solution2 {
    /**
     * https://leetcode-cn.com/problems/merge-sorted-array/
     * 合并两个有序数组
     *
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums1.length == 0 || nums2 == null) {
            return;
        }
        int p = m-- + n-- - 1;// num1和num2合并后的空间大小
        while (m >= 0 && n >= 0) {
            nums1[p--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        }
        // 如果nums1还没合并完，num2合并完了
        // 此时不用管，因为num1自身就是有序的，同时num1还是容器
        // 如果nums1合并完了，num2还没合并完
        while (n > 0) {
            nums1[p--] = nums2[n--];
        }

    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        new Solution2().merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }

}