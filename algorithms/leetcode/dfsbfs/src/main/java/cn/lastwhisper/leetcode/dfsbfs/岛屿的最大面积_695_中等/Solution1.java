package cn.lastwhisper.leetcode.dfsbfs.岛屿的最大面积_695_中等;

import org.junit.Assert;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/max-area-of-island/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：深度优先搜索
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(dfs(grid, i, j, visited), max);
                }
            }
        }
        return max;
    }

    /**
     * @param grid 岛屿
     * @param x 当前所处的x
     * @param y 当前所处的y
     * @param visited 已经被访问的
     */
    public int dfs(int[][] grid, int x, int y, boolean[][] visited) {
        if (x < 0 || x > grid.length - 1 || y < 0 || y > grid[0].length - 1 || visited[x][y] || grid[x][y] == 0) {
            return 0;
        }
        int count = 1;
        visited[x][y] = true;// 不需要回溯，因为到过的地方下次不会再去
        count += dfs(grid, x - 1, y, visited);//上
        count += dfs(grid, x, y + 1, visited);//右
        count += dfs(grid, x + 1, y, visited);//下
        count += dfs(grid, x, y - 1, visited);//左
        return count;
    }

    public static void main(String[] args) {
        //int[][] grid = {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
        //        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
        //        {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
        //        {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
        //        {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
        //        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
        //        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
        //        {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        //};
        int[][] grid = {{1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}};
        Assert.assertEquals(4, new Solution1().maxAreaOfIsland(grid));
    }
}