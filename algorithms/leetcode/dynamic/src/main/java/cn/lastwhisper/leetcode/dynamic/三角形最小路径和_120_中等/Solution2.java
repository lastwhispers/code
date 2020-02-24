package cn.lastwhisper.leetcode.dynamic.三角形最小路径和_120_中等;

import java.util.List;

import static cn.lastwhisper.leetcode.common.matrix.ArrayUtils.createList;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/triangle/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：动态规划——基于思路一
     *      空间优化：
     *          prev暂存dp[i-1][j-1],cur暂存dp[i-1][j]
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        // 特判
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        // dp最大长度==triangle底边长度
        // 题意：只使用 O(n) 的额外空间（n 为三角形的总行数）
        int[] dp = new int[triangle.size()];
        dp[0] = triangle.get(0).get(0);

        // prev暂存dp[i-1][j-1],cur暂存dp[i-1][j]
        int prev = 0, cur;
        for (int i = 1; i < triangle.size(); i++) {
            //对每一行的元素进行推导
            List<Integer> rows = triangle.get(i);
            for (int j = 0; j <= i; j++) {
                cur = dp[j];
                if (j == 0) {
                    // 最左端特殊处理
                    dp[j] = cur + rows.get(j);
                } else if (j == i) {
                    // 最右端特殊处理
                    dp[j] = prev + rows.get(j);
                } else {
                    dp[j] = Math.min(cur, prev) + rows.get(j);
                }
                prev = cur;
            }
        }

        int res = Integer.MAX_VALUE;
        // dp最后一行记录了最小路径
        for (int i = 0; i < triangle.size(); i++) {
            res = Math.min(res, dp[i]);
        }
        return res;
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
        System.err.println(new Solution2().minimumTotal(triangle));
    }
}