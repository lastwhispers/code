package cn.lastwhisper.leetcode.dfsbfs.腐烂的橘子_994_简单;

import java.util.LinkedList;
import java.util.Queue;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/rotting-oranges/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：广度优先搜索
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();

        int count = 0; // count 表示新鲜橘子的数量
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1) {//新鲜
                    count++;
                } else if (grid[r][c] == 2) {//腐烂
                    queue.add(new int[]{r, c});
                }
            }
        }

        int round = 0; // round 表示腐烂的轮数，或者分钟数
        while (count > 0 && !queue.isEmpty()) {
            round++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] orange = queue.poll();
                // 腐烂橘子的坐标
                int r = orange[0];
                int c = orange[1];
                // 上下左右查看，是新鲜的就给它腐烂
                // 腐烂橘子上面的橘子
                if (r - 1 >= 0 && grid[r - 1][c] == 1) {
                    grid[r - 1][c] = 2;
                    count--;
                    queue.add(new int[]{r - 1, c});
                }
                // 腐烂橘子下面的橘子
                if (r + 1 < m && grid[r + 1][c] == 1) {
                    grid[r + 1][c] = 2;
                    count--;
                    queue.add(new int[]{r + 1, c});
                }
                // 腐烂橘子左面的橘子
                if (c - 1 >= 0 && grid[r][c - 1] == 1) {
                    grid[r][c - 1] = 2;
                    count--;
                    queue.add(new int[]{r, c - 1});
                }
                // 腐烂橘子右面的橘子
                if (c + 1 < n && grid[r][c + 1] == 1) {
                    grid[r][c + 1] = 2;
                    count--;
                    queue.add(new int[]{r, c + 1});
                }
            }
        }

        if (count > 0) {
            return -1;
        } else {
            return round;
        }
    }

    public static void main(String[] args) {

    }
}