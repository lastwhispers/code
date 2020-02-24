package cn.lastwhisper.leetcode.dynamic.最小路径和_64_中等.recursion;

public class Solution3 {
    public int calculate(int[][] grid, int i, int j) {
        // 不合法坐标
        if (i == grid.length || j == grid[0].length) return Integer.MAX_VALUE;
        // 终点
        if (i == grid.length - 1 && j == grid[0].length - 1) return grid[i][j];
        return grid[i][j] + Math.min(calculate(grid, i + 1, j), calculate(grid, i, j + 1));
    }

    public int minPathSum(int[][] grid) {
        return calculate(grid, 0, 0);
    }

}