package cn.lastwhisper.leetcode.dynamic.最小路径和_64_中等;

class Solution4 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/minimum-path-sum/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：动态规划——基于思路三
     *    空间优化：
     *        只需要左边和上边的值保存即可，一维数组dp[j]存储下边的值，
     *         dp[j+1]存储右边的值
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

        int[] dp = new int[column ];
        for (int i = row - 1; i >= 0; i--) {
            for (int j = column - 1; j >= 0; j--) {
                if (i == row - 1 && j == column - 1) {
                    dp[j] = grid[i][j];
                } else if (j == column - 1) {
                    dp[j] = dp[j] + grid[i][j];
                } else if (i == row - 1) {
                    dp[j] = dp[j + 1] + grid[i][j];
                } else {
                    dp[j] = Math.min(dp[j], dp[j + 1]) + grid[i][j];
                }
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(new Solution4().minPathSum(grid));
    }
}