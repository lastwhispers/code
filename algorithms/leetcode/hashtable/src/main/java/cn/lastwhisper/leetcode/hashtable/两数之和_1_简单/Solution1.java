package cn.lastwhisper.leetcode.hashtable.两数之和_1_简单;

import java.util.Arrays;

class Solution1 {
    /**
     * https://leetcode-cn.com/problems/two-sum/
     * 核心思想：找出所有组合进行对比，取其中一个即可
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     *
     *
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums == null) throw new IllegalArgumentException("非法参数");
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution1().twoSum(new int[]{2, 7, 11, 15}, 9)));
    }
}