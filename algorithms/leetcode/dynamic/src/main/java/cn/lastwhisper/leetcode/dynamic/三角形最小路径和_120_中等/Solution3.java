package cn.lastwhisper.leetcode.dynamic.三角形最小路径和_120_中等;

import java.util.List;

import static cn.lastwhisper.leetcode.common.array.ArrayUtil.createList;

class Solution3 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/triangle/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：动态规划——自底向上
     *   triangle={
     *              [2],
     *              [3,4],
     *              [6,5,7],
     *              [4,1,8,3]
     *          }
     *  1、状态定义：dp[i][j]表示包含第i行第j列元素的最小路径和
     *  2、状态分析
     *      初始化：
     *          dp[n]=triangle[n]
     *      常规：
     *          triangle[i][j]一定会到达triangle[i+1][j]或者triangle[i+1][j+1]
     *          所以状态dp[i][j]一定等于dp[i+1][j]或者dp[i+1][j+1]的最小值+triangle[i][j]
     *  3、转换方程：dp[i][j]=min(dp[i+1][j],dp[i+1][j+1])+triangle[i][j]
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n^2)
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        // 特判
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        // 加1可以不用初始化最后一行
        // 根据题意，行列值相同
        int[][] dp = new int[triangle.size() + 1][triangle.size() + 1];

        for (int i = triangle.size() - 1; i >= 0; i--) {
            List<Integer> rows = triangle.get(i);
            for (int j = 0; j < rows.size(); j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + rows.get(j);
            }
        }
        return dp[0][0];
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
        System.err.println(new Solution3().minimumTotal(triangle));
    }
}