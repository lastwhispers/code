package cn.lastwhisper.leetcode.week.one175.参加考试的最大学生数_5335_困难;

import java.util.ArrayList;
import java.util.List;

class Solution {
    private static final int[][] directory = new int[][]{
            {-1, 0},// up
            {0, 1},// right
            {1, 0},// down
            {0, -1},// left
    };
    private int[][] dir = new int[][]{
            {0, -1}, // 左
            {-1, -1},// 左上
            {-1, 1}, // 右上
            {0, 1},  // 右
    };
    private int max = 0, num = 0;
    private int m, n;

    public int maxStudents(char[][] seats) {
        m = seats.length;
        n = seats[0].length;
        boolean[][] visited = new boolean[m][n];
        List<List<String>> resultList = new ArrayList<>();
        dfs(seats, visited, 0, 0, new ArrayList<>(), resultList);
        return max;
    }

    public void dfs(char[][] seats, boolean[][] visited, int startX, int startY, List<String> result, List<List<String>> resultList) {
        if (startX == m - 1 && startY == n - 1) {
            max = Math.max(max, num);
            //max = Math.max(max, result.size());
            resultList.add(new ArrayList<>(result));
            return;
        }
        for (int i = 0; i < directory.length; i++) {
            int newX = startX + directory[i][0];
            int newY = startY + directory[i][1];
            if (isArea(newX, newY) && seats[newX][newY] == '.' && isValid(visited, newX, newY)) {
                visited[newX][newY] = true;
                result.add(newX + "," + newY);
                num++;
                dfs(seats, visited, newX, newY, result, resultList);
                visited[newX][newY] = false;
                result.remove(result.size() - 1);
                num--;
            }
        }
    }

    private boolean isArea(int x, int y) {
        return x >= 0 && y >= 0 && x < m && y < n;
    }


    // 左侧、右侧、左上、右上没人
    public boolean isValid(boolean[][] visited, int startX, int startY) {
        for (int[] ints : dir) {
            int newX = startX + ints[0];
            int newY = startY + ints[1];
            if (isArea(newX, newY) && visited[newX][newY]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] seats = {{'#', '.', '#', '#', '.', '#'},
                {'.', '#', '#', '#', '#', '.'},
                {'#', '.', '#', '#', '.', '#'}
        };
        System.out.println(new Solution().maxStudents(seats));
    }
}