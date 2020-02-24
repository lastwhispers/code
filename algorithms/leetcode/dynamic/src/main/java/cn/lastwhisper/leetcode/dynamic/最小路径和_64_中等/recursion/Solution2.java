package cn.lastwhisper.leetcode.dynamic.最小路径和_64_中等.recursion;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/minimum-path-sum/
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

    public int minPathSum(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        return findMin(grid, 0, 0);
    }

    private int findMin(int[][] grid, int startX, int startY) {
        if (m - 1 == startX && n - 1 == startY) {
            return grid[startX][startY];
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < dir.length; i++) {
            int newX = startX + dir[i][0];
            int newY = startY + dir[i][1];
            if (isValid(newX, newY)) {
                result = Math.min(result, grid[startX][startY] + findMin(grid, newX, newY));
            }
        }
        return result;
    }

    private boolean isValid(int x, int y) {
        return x < m && y < n;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println(new Solution2().minPathSum(grid));
    }
}