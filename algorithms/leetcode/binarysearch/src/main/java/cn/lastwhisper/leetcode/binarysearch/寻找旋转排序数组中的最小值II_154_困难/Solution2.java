package cn.lastwhisper.leetcode.binarysearch.寻找旋转排序数组中的最小值II_154_困难;

class Solution2 {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[right]) left = mid + 1;
            else if (nums[mid] < nums[right]) right = mid;
            else right = right - 1;
        }
        return nums[left];
    }

    public static void main(String[] args) {
        //int[] nums = new int[]{3, 4, 5, 1, 2};
        //int[] nums = new int[]{0,0,0,0};
        int[] nums = new int[]{1, 0, 1, 1, 1};//01111
        //int[] nums = new int[]{1,1,1,0,1};//01111
        //int[] nums = new int[]{0, 1, 1, 1, 1};//01111
        System.err.println(new Solution2().findMin(nums));
    }
}