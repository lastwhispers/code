package cn.lastwhisper.leetcode.dynamic.三角形最小路径和_120_中等;

import java.util.List;

import static cn.lastwhisper.leetcode.common.array.ArrayUtil.createList;

class Solution4 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/triangle/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：动态规划——基于思路三
     *      空间优化：
     *
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        // 特判
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        // dp中记录了求第i行时，第i+1的最小路径和
        int[] dp = new int[triangle.size() + 1];

        for (int i = triangle.size() - 1; i >= 0; i--) {
            List<Integer> rows = triangle.get(i);
            for (int j = 0; j < rows.size(); j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + rows.get(j);
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        int[][] arrays = new int[][]{
                {2},
                {3, 4},
                {6, 5, 7},
                {4, 1, 8, 3}
        };
        //int[][] arrays = new int[][]{{-10}};
        List<List<Integer>> triangle = createList(arrays);
        System.err.println(new Solution4().minimumTotal(triangle));
    }
}