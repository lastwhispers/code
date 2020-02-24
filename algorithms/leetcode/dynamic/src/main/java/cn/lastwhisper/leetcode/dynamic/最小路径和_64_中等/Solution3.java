package cn.lastwhisper.leetcode.dynamic.最小路径和_64_中等;

class Solution3 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/minimum-path-sum/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：动态规划——自底向上
     *  1、状态定义：dp[i][j]表示包含第i行第j列元素的最小路径和
     *  2、状态分析
     *      初始：
     *          dp[m][n]=grid[m][n]
     *      常规：
     *          grid[i][j]一定会经过grid[i][j+1]或者grid[i+1][j]
     *          所以状态dp[i][j]一定等于dp[i][j+1]或者dp[i+1][j]的最小值+grid[i][j]
     *          状态转换方程：
     *              dp[i][j]=min(dp[i][j+1],dp[i+1][j])+grid[i][j]
     *      特殊：
     *          矩阵m*n
     *          grid[i][n]没有右边 只能从下边grid[i+1][n]经过
     *          grid[m][j]没有下边 只能从右边grid[m][j+1]经过
     *          状态转换方程：
     *              dp[i][n]=dp[i+1][n]+grid[i][j]
     *              dp[m][j]=dp[m][j+1]+grid[i][j]
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n^2)
     */
    public int minPathSum(int[][] grid) {
        // 特判
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int row = grid.length;
        int column = grid[0].length;

        int[][] dp = new int[row][column];
        for (int i = row - 1; i >= 0; i--) {
            for (int j = column - 1; j >= 0; j--) {
                if (i == row - 1 && j == column - 1) {
                    dp[i][j] = grid[i][j];
                } else if (j == column - 1) {
                    dp[i][j] = dp[i + 1][j] + grid[i][j];
                } else if (i == row - 1) {
                    dp[i][j] = dp[i][j + 1] + grid[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + grid[i][j];
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
        System.out.println(new Solution3().minPathSum(grid));
    }
}