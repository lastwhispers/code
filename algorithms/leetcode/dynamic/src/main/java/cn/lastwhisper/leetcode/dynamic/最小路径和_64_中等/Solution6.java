package cn.lastwhisper.leetcode.dynamic.最小路径和_64_中等;

class Solution6 {
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
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 起点
                if (i == 0 && j == 0) continue;
                    // 第一行，必然是从左边来
                else if (i == 0) grid[i][j] = grid[i][j - 1] + grid[i][j];
                    // 第一列，必然是从上边来
                else if (j == 0) grid[i][j] = grid[i - 1][j] + grid[i][j];
                    // 左边或右边
                else grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(new Solution6().minPathSum(grid));
    }
}