package cn.lastwhisper.leetcode.dynamic.最小路径和_64_中等;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/minimum-path-sum/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：动态规划——自顶向下
     *  1、状态定义：dp[i][j]表示包含第i行第j列元素的最小路径和
     *  2、状态分析
     *      初始：
     *          dp[0][0]=grid[0][0]
     *      常规：
     *          grid[i][j]一定会经过grid[i][j-1]或者grid[i-1][j]
     *          所以状态dp[i][j]一定等于dp[i][j-1]或者dp[i-1][j]的最小值+grid[i][j]
     *          状态转换方程：
     *              dp[i][j]=min(dp[i][j-1],dp[i-1][j])+grid[i][j]
     *      特殊：
     *          grid[i][0]没有左边 只能从上边grid[i-1][0]经过
     *          grid[0][j]没有上边 只能从左边grid[0][j-1]经过
     *          状态转换方程：
     *              dp[i][0]=dp[i-1][0]+grid[i][j]
     *              dp[0][j]=dp[0][j-1]+grid[i][j]
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
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (i == 0 && j == 0) {
                    // 起点
                    dp[0][0] = grid[0][0];
                } else if (i == 0) {
                    // 从左边来
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    // 从上面来
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
                }
            }
        }

        return dp[row - 1][column - 1];
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(new Solution1().minPathSum(grid));
    }
}