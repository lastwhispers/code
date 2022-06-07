package cn.cunchang.岛屿的最大面积_695_中等;

import javafx.util.Pair;

import java.util.Stack;

class Solution2 {
    /**
     * 栈模拟递归
     */
    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        int x, y;
        int[] direction = new int[]{-1, 0, 1, 0, -1};
        Stack<Pair<Integer, Integer>> stack = new Stack<>();
        // 对搜索过的岛屿进行标记
        int[][] visited = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && visited[i][j] == 0) {
                    int areaCount = 1;
                    visited[i][j] = 1;//当前节点已经访问
                    stack.push(new Pair<>(i, j));

                    while (!stack.isEmpty()) {
                        Pair<Integer, Integer> pair = stack.pop();
                        for (int k = 0; k < 4; ++k) {
                            x = pair.getKey() + direction[k];
                            y = pair.getValue() + direction[k + 1];
                            if (checkArea(grid, visited, x, y)) {
                                areaCount++;
                                visited[x][y] = 1;
                                stack.push(new Pair<>(x, y));
                            }
                        }

                    }

                    res = Math.max(res, areaCount);
                }
            }
        }
        return res;
    }

    private boolean checkArea(int[][] grid, int[][] visited, int x, int y) {
        // i,j未超界
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
            return false;
        }
        // 已经来过不处理
        if (visited[x][y] == 1 || grid[x][y] == 0) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] x = {
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}};
        System.out.println(new Solution2().maxAreaOfIsland(x));
    }

}