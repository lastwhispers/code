package cn.lastwhisper.leetcode.dynamic.不同路径II_63_中等;

class Solution2 {

    /**
     * 题目地址：https://leetcode-cn.com/problems/unique-paths-ii/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：动态规划
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1)
                    continue;
                if (i >= 1)
                    dp[i][j] = dp[i][j] + dp[i - 1][j];
                if (j >= 1)
                    dp[i][j] = dp[i][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int[][] obstacleGrid = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        //int[][] obstacleGrid = {
        //        {1},
        //};
        System.out.println(new Solution2().uniquePathsWithObstacles(obstacleGrid));
    }
}