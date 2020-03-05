package cn.lastwhisper.leetcode.binarysearch.寻找旋转排序数组中的最小值_153_中等;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
     * -------------------------------------------------------------------
     * 思考：
     *  输入特征：
     *      1、两个连续有序部分
     *      2、nums[0]>num[n]
     *      3、数组内无重复
     * -------------------------------------------------------------------
     * 思路：
     *  一、遍历
     *  二、二分
     * -------------------------------------------------------------------
     * 时间复杂度：O(logn)
     * 空间复杂度：O(n)
     */
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1, middle = left;

        while (nums[left] > nums[right]) {
            // 找到最小值
            if (right - left == 1) {
                middle = right;
                break;
            }
            middle = (right - left) / 2 + left;

            if (nums[middle] > nums[left]) {
                // middle在左数组
                left = middle;
            } else if (nums[middle] < nums[right]) {
                // middle在右数组
                right = middle;
            }
        }
        return nums[middle];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,4,5,1,2};

        System.err.println(new Solution1().findMin(nums));
    }
}