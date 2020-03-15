package cn.lastwhisper.leetcode.week.one180.矩阵中的幸运数_5356_简单;

import java.util.ArrayList;
import java.util.List;

class Solution {

    public List<Integer> luckyNumbers(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (luckNum(i, j, matrix)) {
                    res.add(matrix[i][j]);
                }
            }
        }
        return res;
    }

    private boolean luckNum(int x, int y, int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                // 在同一行的所有元素中最小
                if (i == x && matrix[x][y] > matrix[i][j]) {
                    return false;
                }
                // 在同一列的所有元素中最大
                if (j == y && matrix[x][y] < matrix[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] matrix = {{3, 7, 8}, {9, 11, 13}, {15, 16, 17}};
        System.err.println(new Solution().luckyNumbers(matrix));
    }

}