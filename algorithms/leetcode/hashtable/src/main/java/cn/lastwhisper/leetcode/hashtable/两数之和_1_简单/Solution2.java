package cn.lastwhisper.leetcode.hashtable.两数之和_1_简单;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution2 {
    /**
     * https://leetcode-cn.com/problems/two-sum/
     * 核心思想：先排序O(nlogn),在对撞指针O(n)——此方法不行，详情见main方法
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(1)
     * 相关题型：两数之和_II_输入有序数组_167
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums == null) throw new IllegalArgumentException("非法参数");
        // 存储原数组，下标和值映射关系
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        Arrays.sort(nums);
        // 对撞指针
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int left = nums[l];
            int right = nums[r];
            if ((left + right) == target) {
                return new int[]{map.get(left), map.get(right)};
            } else if ((left + right) > target) {
                r--;
            } else {
                l++;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        //int[] arr = new int[]{3, 2, 4};
        //int target = 6;

        int[] arr = new int[]{3,3};//此时发生错误
        int target = 6;
        System.out.println(Arrays.toString(new Solution2().twoSum(arr, target)));
    }
}