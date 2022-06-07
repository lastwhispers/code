package cn.cunchang.岛屿的最大面积_695_中等;

class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        // 对搜索过的岛屿进行标记
        int[][] mark = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int tmp = dfs(grid, i, j, mark);
                res = Math.max(res, tmp);
            }
        }
        return res;
    }

    private int dfs(int[][] grid, int i, int j, int[][] mark) {
        // i,j未超界
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return 0;
        }
        // 已经来过不处理
        if (mark[i][j] == 1 || grid[i][j] == 0) {
            return 0;
        }
        mark[i][j] = 1;// 标记已经来过
        int res = 1;
        res += dfs(grid, i - 1, j, mark);//上
        res += dfs(grid, i + 1, j, mark);//下
        res += dfs(grid, i, j - 1, mark);//左
        res += dfs(grid, i, j + 1, mark);//右
        return res;
    }

}