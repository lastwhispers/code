package cn.lastwhisper.leetcode.week.one176.统计有序矩阵中的负数_5340;

class Solution {
    // 降序
    public int countNegatives(int[][] grid) {
        int count = 0;
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                // 跳出当前循环
                if (grid[i][j] >= 0) {
                    break;
                }
                if (grid[i][j] < 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] grid = {{4, 3, 2, -1}, {3, 2, 1, -1}, {1, 1, -1, -2}, {-1, -1, -2, -3}};//8
        System.out.println(new Solution().countNegatives(grid));
    }
}