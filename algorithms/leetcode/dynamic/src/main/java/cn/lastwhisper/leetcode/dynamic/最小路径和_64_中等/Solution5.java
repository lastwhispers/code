package cn.lastwhisper.leetcode.dynamic.最小路径和_64_中等;

class Solution5 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/minimum-path-sum/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：动态规划，从上到下
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int minPathSum(int[][] grid) {
        // i是y,j是x
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // 最后一行非最后一列
                if (i == m - 1 && j != n - 1) {
                    dp[i][j] = grid[i][j] + dp[i][j + 1];
                }
                // 最后一列非最后一行
                else if (j == n - 1 && i != m - 1) {
                    dp[i][j] = grid[i][j] + dp[i + 1][j];
                }
                // 上或左最小
                else if (j != n - 1 && i != m - 1) {
                    dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
                }
                // 起点
                else {
                    dp[i][j] = grid[i][j];
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(new Solution5().minPathSum(grid));
    }
}