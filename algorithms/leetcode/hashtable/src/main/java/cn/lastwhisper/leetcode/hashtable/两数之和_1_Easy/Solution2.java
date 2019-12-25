package cn.lastwhisper.leetcode.hashtable.两数之和_1_Easy;

import java.util.Arrays;

class Solution2 {
    /**
     * https://leetcode-cn.com/problems/two-sum/
     * 核心思想：先排序O(nlogn),在对撞指针O(n)
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(1)
     * 相关题型：两数之和_II_输入有序数组_167
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums == null) throw new IllegalArgumentException("非法参数");
        Arrays.sort(nums);
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int left = nums[l];
            int right = nums[r];
            if ((left + right) == target) {
                return new int[]{l, r};
            } else if ((left + right) > target) {
                r--;
            } else {
                l++;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                new Solution2() .twoSum(new int[]{2, 7, 11, 15}, 22)));
    }
}