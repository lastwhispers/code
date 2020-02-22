package cn.lastwhisper.leetcode.dynamic.不同路径II_63_中等;

class Solution1 {

    /**
     * 题目地址：https://leetcode-cn.com/problems/unique-paths-ii/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：递归
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    private int m, n;
    private int[][] dir = {{0, 1}, {1, 0}};
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        m = obstacleGrid.length;
        n = obstacleGrid[0].length;
        return findPath(obstacleGrid, 0, 0);
    }

    private int findPath(int[][] grid, int startX, int startY) {
        if (m - 1 == startX && n - 1 == startY) {
            return 1;
        }
        int result = 0;
        for (int i = 0; i < dir.length; i++) {
            int newX = startX + dir[i][0];
            int newY = startY + dir[i][1];
            if (isValid(newX, newY, grid)) {
                result += findPath(grid, newX, newY);
            }
        }
        return result;
    }

    private boolean isValid(int x, int y, int[][] grid) {
        return x < m && y < n && grid[x][y] != 1;
    }

    public static void main(String[] args) {
        //int[][] obstacleGrid = {
        //        {0, 0, 0},
        //        {0, 1, 0},
        //        {0, 0, 0}
        //};
        int[][] obstacleGrid = {
                {1},
        };
        System.out.println(new Solution1().uniquePathsWithObstacles(obstacleGrid));
    }
}