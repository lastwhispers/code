package cn.lastwhisper.leetcode.recurionbacktracking.N皇后II_52_困难;

import java.util.ArrayList;
import java.util.List;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/n-queens-ii/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：递归+回溯
     *  递归：
     *      1.当前列没有其他棋子
     *      2.右上到左下对角线没有其他棋子
     *      3.左上到右下对角线没有其他棋子
     *  回溯：
     *  递归终止：
     *       index==n时，解集数量增加
     * -------------------------------------------------------------------
     * 时间复杂度：O(n*(n+1))
     * 空间复杂度：
     */
    private boolean[] col;//列
    private boolean[] diagonal1;//右上到左下对角线
    private boolean[] diagonal2;//左上到右下对角线
    private int result;

    public int totalNQueens(int n) {

        col = new boolean[n];
        diagonal1 = new boolean[2 * n - 1];
        diagonal2 = new boolean[2 * n - 1];
        putQ(n, 0);

        return result;
    }

    /**
     * 尝试在一个n皇后问题中, 摆放第index行的皇后位置
     *
     * @param n n皇后
     * @param index 当前处于第index行
     */
    private void putQ(int n, int index) {
        if (n == index) {
            result++;
            return;
        }
        // n列
        for (int j = 0; j < n; j++) {
            // 在第index行，第j列是否可以放
            if (!col[j] && !diagonal1[index + j] && !diagonal2[index - j + n - 1]) {
                col[j] = true;
                diagonal1[index + j] = true;
                diagonal2[index - j + n - 1] = true;
                putQ(n, index + 1);
                col[j] = false;
                diagonal1[index + j] = false;
                diagonal2[index - j + n - 1] = false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution1().totalNQueens(4));
        ;
    }
}