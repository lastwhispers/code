package cn.lastwhisper.offer.面试题03_数组中重复的数字_简单;

import java.util.HashSet;
import java.util.Set;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
     * -------------------------------------------------------------------
     * 思考：
     *  1、hash查重
     *  2、找规律(桶思想)
     * -------------------------------------------------------------------
     * 思路：hash查重
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int findRepeatNumber(int[] nums) {
        Set<Integer> hash = new HashSet<>();
        for (int num : nums) {
            if (hash.contains(num)) {
                return num;
            }
            hash.add(num);
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution1().findRepeatNumber(new int[]{2, 3, 1, 0, 2, 5, 3}));
    }
}