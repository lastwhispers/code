package cn.lastwhisper.leetcode.binarysearch.寻找旋转排序数组中的最小值II_154_困难;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/
     * -------------------------------------------------------------------
     * 思考：
     *  输入特征：
     *      1、两个连续有序部分
     *      2、nums[0]>=num[n]
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

        while (nums[left] >= nums[right]) {
            // 找到最小值
            if (right - left == 1) {
                middle = right;
                break;
            }
            middle = (right - left) / 2 + left;

            // 无法界定middle属于前数组还是后数组 {1, 0, 1, 1, 1};//01111
            if (nums[left] == nums[middle] && nums[right] == nums[middle]) {
                return sequenceSearch(left, right, nums);
            }

            if (nums[middle] >= nums[left]) {
                left = middle;
            } else if (nums[middle] <= nums[right]) {
                right = middle;
            }
        }
        return nums[middle];
    }

    // 顺序查找
    public int sequenceSearch(int left, int right, int[] nums) {
        int min = nums[left];
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        return min;
    }


}