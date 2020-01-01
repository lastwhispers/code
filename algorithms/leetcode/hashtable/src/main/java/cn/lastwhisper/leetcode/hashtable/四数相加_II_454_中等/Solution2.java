package cn.lastwhisper.leetcode.hashtable.四数相加_II_454_中等;

import java.util.HashMap;
import java.util.Map;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/4sum-ii/
     * -------------------------------------------------------------------
     * 优化思路：
     *  （3）使用hash存储CD的所有情况，时间复杂度O(n^2)
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n^2)
     * -------------------------------------------------------------------
     * 执行用时 :142 ms, 在所有 java 提交中击败了27.37%的用户
     * 内存消耗 :59 MB, 在所有 java 提交中击败了90.62%的用户
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        // key:数组中某个数、value:该数出现的频率
        Map<Integer, Integer> cdMap = new HashMap<>();
        for (int c : C) {
            for (int d : D) {
                int cd = c + d;
                if (cdMap.containsKey(cd)) {
                    cdMap.put(cd, cdMap.get(cd) + 1);
                } else {
                    cdMap.put(cd, 1);
                }
            }
        }

        int count = 0;
        for (int a : A) {
            for (int b : B) {
                int cd = 0 - a - b;
                Integer cdCount = cdMap.get(cd);
                if (cdCount != null) {
                    count += cdCount;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] A = {1, 2};
        int[] B = {-2, -1};
        int[] C = {-1, 2};
        int[] D = {0, 2};
        System.out.println(new Solution2().fourSumCount(A, B, C, D));
    }
}