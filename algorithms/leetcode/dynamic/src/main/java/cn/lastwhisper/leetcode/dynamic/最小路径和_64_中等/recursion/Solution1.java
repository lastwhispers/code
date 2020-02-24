package cn.lastwhisper.leetcode.dynamic.最小路径和_64_中等.recursion;

class Solution1 {
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
    private int min=Integer.MAX_VALUE, m, n;
    private int[][] dir = {{0, 1}, {1, 0}};

    public int minPathSum(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        findMin(grid, 0, 0, grid[0][0]);
        return min;
    }

    /**
     * 此种递归无法缓存中间值重复使用
     */
    private void findMin(int[][] grid, int startX, int startY, int result) {
        // 终点
        if (m - 1 == startX && n - 1 == startY) {
            min = Math.min(min, result);
            return;
        }
        for (int i = 0; i < dir.length; i++) {
            int newX = startX + dir[i][0];
            int newY = startY + dir[i][1];
            // 防止不合法坐标
            if (isValid(newX, newY)) {
                findMin(grid, newX, newY, grid[newX][newY] + result);
            }
        }
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
        System.out.println(new Solution1().minPathSum(grid));
    }
}