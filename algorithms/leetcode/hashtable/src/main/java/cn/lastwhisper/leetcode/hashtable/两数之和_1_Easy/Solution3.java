package cn.lastwhisper.leetcode.hashtable.两数之和_1_Easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution3 {
    /**
     * https://leetcode-cn.com/problems/two-sum/
     * 核心思想：
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * 执行用时 : 3 ms , 在所有 Java 提交中击败了 99.01% 的用户
     * 内存消耗 : 37.9 MB , 在所有 Java 提交中击败了 79.09% 的用户
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums == null) throw new IllegalArgumentException("非法参数");
        // map存储数字与对应下标
        Map<Integer, Integer> map = new HashMap<>();
        // 边记录边找
        for (int i = 0; i < nums.length; i++) {
            int key = target-nums[i];
            if(map.containsKey(key)){
                return new int[]{i,map.get(key)};
            }
            map.put(nums[i],i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution3().twoSum(new int[]{2, 7, 11, 15}, 9)));
    }
}