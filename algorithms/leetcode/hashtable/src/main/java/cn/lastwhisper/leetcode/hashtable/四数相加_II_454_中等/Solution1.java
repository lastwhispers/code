package cn.lastwhisper.leetcode.hashtable.四数相加_II_454_中等;

import java.util.HashMap;
import java.util.Map;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/4sum-ii/
     * -------------------------------------------------------------------
     * 思考：
     * 数据特征：
     *     输入：数组、无序、所有整数
     *     输出：二维数组
     * -------------------------------------------------------------------
     * 优化思路：
     *  （1）四层循环，时间复杂度O(n^4)——超时
     *  （2）使用hash存储D的所有情况，时间复杂度O(n^3)——超时
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        // key:数组中某个数、value:该数出现的频率
        Map<Integer, Integer> dMap = new HashMap<>();
        for (int dKey : D) {
            if (dMap.containsKey(dKey)) {
                dMap.put(dKey, dMap.get(dKey) + 1);
            } else {
                dMap.put(dKey, 1);
            }
        }
        int count = 0;
        for (int a : A) {
            for (int b : B) {
                for (int c : C) {
                    int dKey = 0 - a - b - c;
                    Integer dCount = dMap.get(dKey);
                    if (dCount != null) {
                        count += dCount;
                    }
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
        System.out.println(new Solution1().fourSumCount(A, B, C, D));
    }
}