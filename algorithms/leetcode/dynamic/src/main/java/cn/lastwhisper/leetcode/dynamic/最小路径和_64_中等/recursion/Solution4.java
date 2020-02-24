package cn.lastwhisper.leetcode.dynamic.最小路径和_64_中等.recursion;

class Solution4 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/minimum-path-sum/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：递归+记忆化搜索
     * -------------------------------------------------------------------
     * 时间复杂度：O(2^(m+n))。每次移动最多可以有两种选择。
     * 空间复杂度：O(m+n)。递归的深度是 m+n。
     */
    private int m, n;
    private int[][] dir = {{0, 1}, {1, 0}};

    public int minPathSum(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int[][] memo = new int[m][n];
        return findMin(grid, 0, 0, memo);
    }

    private int findMin(int[][] grid, int startX, int startY, int[][] memo) {
        // 终点
        if (m - 1 == startX && n - 1 == startY) {
            return grid[startX][startY];
        }
        if (memo[startX][startY] != 0) {
            return memo[startX][startY];
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < dir.length; i++) {
            int newX = startX + dir[i][0];
            int newY = startY + dir[i][1];
            if (isValid(newX, newY)) {
                result = Math.min(result, grid[startX][startY] + findMin(grid, newX, newY, memo));
            }
        }
        memo[startX][startY] = result;
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
        System.out.println(new Solution4().minPathSum(grid));
    }
}