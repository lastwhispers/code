package cn.lastwhisper.leetcode.dynamic.三角形最小路径和_120_中等;

import java.util.List;

import static cn.lastwhisper.leetcode.common.array.ArrayUtil.createList;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/triangle/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：动态规划——自顶向下
     *   triangle={
     *              [2],
     *              [3,4],
     *              [6,5,7],
     *              [4,1,8,3]
     *          }
     *  1、状态定义：dp[i][j]表示包含第i行第j列元素的最小路径和
     *  2、状态分析
     *      初始化：
     *          dp[0][0]=triangle[0][0]
     *      常规：
     *          triangle[i][j]一定会经过triangle[i-1][j]或者triangle[i-1][j-1]
     *          所以状态dp[i][j]一定等于dp[i-1][j]或者dp[i-1][j-1]的最小值+triangle[i][j]
     *      特殊：
     *          triangle[i][0]没有左上角 只能从triangle[i-1][j]经过
     *          triangle[i][row[0].length]没有上面 只能从triangle[i-1][j-1]经过
     *  3、转换方程：dp[i][j]=min(dp[i-1][j],dp[i-1][j-1])+triangle[i][j]
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n^2)
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        // 特判
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }

        int row = triangle.size();
        int column = triangle.get(row - 1).size();

        int[][] dp = new int[row][column];
        dp[0][0] = triangle.get(0).get(0);

        for (int i = 1; i < row; i++) {
            //对每一行的元素进行最小路径和推导
            List<Integer> rows = triangle.get(i);
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    // 最左端特殊处理
                    dp[i][j] = dp[i - 1][j] + rows.get(j);
                } else if (j == i) {
                    // 最右端特殊处理
                    dp[i][j] = dp[i - 1][j - 1] + rows.get(j);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + rows.get(j);
                }
            }
        }

        int res = Integer.MAX_VALUE;
        // dp最后一行记录了最小路径
        for (int i = 0; i < column; i++) {
            res = Math.min(res, dp[row - 1][i]);
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
        System.err.println(new Solution1().minimumTotal(triangle));
    }
}