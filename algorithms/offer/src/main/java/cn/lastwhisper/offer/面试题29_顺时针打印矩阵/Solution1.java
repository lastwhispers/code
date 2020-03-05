package cn.lastwhisper.offer.面试题29_顺时针打印矩阵;

import java.util.Arrays;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n^2)
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return new int[0];
        int[] result = new int[matrix.length * matrix[0].length];
        // nowColumn当前所在列、totalColumn总列数、nowRow当前所在行、totalRow总行数
        int nowColumn = 0, totalColumn = matrix[0].length - 1, nowRow = 0, totalRow = matrix.length - 1;
        int index = 0;
        // 顺时针遍历
        while (nowColumn <= totalColumn && nowRow <= totalRow) {
            //只有一列，不需要从左到右
            for (int i = nowColumn; i <= totalColumn; i++) {//第一步 从左到右
                result[index++] = matrix[nowRow][i];
            }
            //只有一行，不需要从上到下
            for (int i = nowRow + 1; i <= totalRow; i++) {//第二步 从上到下
                result[index++] = matrix[i][totalColumn];
            }
            if (nowRow != totalRow) {//同行，不需要从右向左遍历
                for (int i = totalColumn - 1; i >= nowColumn; i--) {//第三步 从右向左
                    result[index++] = matrix[totalRow][i];
                }
            }
            if (nowColumn != totalColumn) {//同列，不需要从下到上遍历
                for (int i = totalRow - 1; i > nowRow; i--) {// 第四步 从下到上
                    result[index++] = matrix[i][nowColumn];
                }
            }
            nowColumn++;
            nowRow++;
            totalColumn--;
            totalRow--;
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(Arrays.toString(new Solution1().spiralOrder(matrix)));
    }
}