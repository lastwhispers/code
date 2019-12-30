package cn.lastwhisper.leetcode.hashtable.两数之和_1_简单;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution3 {
    /**
     * https://leetcode-cn.com/problems/two-sum/
     * 核心思想：将所有元素放入查找表，之后对于每一个元素a，查找 target - a 是否存在
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
        //int[] arr = new int[]{3, 2, 4};
        //int target = 6;

        int[] arr = new int[]{3,3};//此时发生错误
        int target = 6;

        //int[] arr = new int[]{2, 7, 11, 15};
        //int target = 9;

        System.out.println(Arrays.toString(new Solution2().twoSum(arr, target)));
    }
}