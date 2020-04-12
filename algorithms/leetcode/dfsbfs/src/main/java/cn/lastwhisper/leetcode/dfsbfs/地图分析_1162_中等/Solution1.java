package cn.lastwhisper.leetcode.dfsbfs.地图分析_1162_中等;

import org.junit.Assert;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/as-far-from-land-as-possible/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：广度优先搜索
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int maxDistance(int[][] grid) {
        int max = 0;

        //boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(dfs(grid, i, j), max);
                }
            }
        }
        return max;
    }

    public int dfs(int[][] grid, int i, int j) {
        int max = 0;
        for (int k = 0; k < grid.length; k++) {
            for (int l = 0; l < grid[0].length; l++) {
                //if (k == i && l == j) {
                //    continue;
                //}
                if (grid[k][l] == 0) {
                    max = Math.max(manhattanDistance(i, k, j, l), max);
                }
            }
        }
        return max;
    }

    /**
     * 曼哈顿距离
     */
    public int manhattanDistance(int x0, int x1, int y0, int y1) {
        return Math.abs(x0 - x1) + Math.abs(y0 - y1);
    }

    public static void main(String[] args) {
        Assert.assertEquals(2, new Solution1().maxDistance(new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}}));
    }
}