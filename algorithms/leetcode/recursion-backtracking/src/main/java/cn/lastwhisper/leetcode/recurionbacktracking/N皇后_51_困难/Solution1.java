package cn.lastwhisper.leetcode.recurionbacktracking.N皇后_51_困难;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static cn.lastwhisper.leetcode.common.print.PrintUtils.printStringLists;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/n-queens/
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
     *       index==n时，根据n和列生成对应的“n皇后”棋盘
     * -------------------------------------------------------------------
     * 时间复杂度：O(n!)
     * 空间复杂度：O(n) //对角线
     */
    private boolean[] col;//列
    private boolean[] diagonal1;//右上到左下对角线
    private boolean[] diagonal2;//左上到右下对角线

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }
        col = new boolean[n];
        diagonal1 = new boolean[2 * n - 1];
        diagonal2 = new boolean[2 * n - 1];

        putQ(n, 0, new ArrayList<>(n), result);
        return result;
    }

    /**
     * 尝试在一个n皇后问题中, 摆放第index行的皇后位置
     *
     * @param n n皇后
     * @param row 当前处于第row行
     * @param columns 已经摆放的列位置
     */
    private void putQ(int n, int row, List<Integer> columns, List<List<String>> result) {
        if (n == row) {
            result.add(generateBoard(n, columns));
            return;
        }
        // n列
        for (int col = 0; col < n; col++) {
            // 在第row行，第j列是否可以放
            if (!this.col[col] && !diagonal1[row + col] && !diagonal2[row - col + n - 1]) {
                columns.add(col);
                this.col[col] = true;
                diagonal1[row + col] = true;
                diagonal2[row - col + n - 1] = true;
                putQ(n, row + 1, columns, result);
                columns.remove(columns.size() - 1);
                this.col[col] = false;
                diagonal1[row + col] = false;
                diagonal2[row - col + n - 1] = false;
            }
        }
    }

    /**
     * 生成nQueue的一种可能
     * @param n nQueue
     * @param column 棋子所在行和列
     */
    private List<String> generateBoard(int n, List<Integer> column) {
        assert column.size() == n;
        ArrayList<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // 每一行
            char[] charArray = new char[n];
            Arrays.fill(charArray, '.');
            // 第i行第column[i]列
            charArray[column.get(i)] = 'Q';
            board.add(new String(charArray));
        }
        return board;
    }

    public static void main(String[] args) {
        printStringLists(new Solution1().solveNQueens(4));
    }
}