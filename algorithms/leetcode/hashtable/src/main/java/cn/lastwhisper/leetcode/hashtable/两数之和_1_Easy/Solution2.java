package cn.lastwhisper.leetcode.hashtable.两数之和_1_Easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

class Solution2 {
    /**
     * https://leetcode-cn.com/problems/two-sum/
     * 核心思想：先排序O(nlogn),在双索引O(n)
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(1)
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums == null) throw new IllegalArgumentException("非法参数");


        return null;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution2().twoSum(new int[]{2, 7, 11, 15}, 9)));
    }
}