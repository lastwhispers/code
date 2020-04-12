package cn.lastwhisper.leetcode.recurionbacktracking.N皇后_51_困难;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static cn.lastwhisper.leetcode.common.print.PrintUtil.printStringLists;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/n-queens/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：递归+回溯
     *  递归：
     *  回溯：
     *  递归终止：
     * -------------------------------------------------------------------
     * 时间复杂度：O(n!)
     * 空间复杂度：O(n) //对角线
     */
    private static List<String> charToString(char[][] array) {
        List<String> result = new LinkedList<>();
        for (char[] chars : array) {
            result.add(String.valueOf(chars));
        }
        return result;
    }

    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) return null;
        List<List<String>> result = new LinkedList<>();
        char[][] board = new char[n][n];
        for (char[] chars : board) Arrays.fill(chars, '.');
        backtrack(board, 0, result);
        return result;
    }

    /**
     * 路径：board中小于row的那些行都已经成功放置了皇后
     * 可选择列表: 第row行的所有列都是放置Q的选择
     * 结束条件: row超过board的最后一行
     *
     * @param board
     * @param row
     */
    private void backtrack(char[][] board, int row, List<List<String>> result) {
        if (row == board.length) {
            result.add(charToString(board));
            return;
        }
        int n = board[row].length;
        for (int col = 0; col < n; col++) {
            if (isValid(board, row, col)) {
                board[row][col] = 'Q';
                backtrack(board, row + 1, result);
                board[row][col] = '.';
            }
        }
    }

    private boolean isValid(char[][] board, int row, int col) {
        int rows = board.length;
        // 垂直方向
        for (char[] chars : board) if (chars[col] == 'Q') return false;
        // 右上
        for (int i = row - 1, j = col + 1; i >= 0 && j < rows; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        // 左上
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }

    public static void main(String[] args) {
        printStringLists(new Solution2().solveNQueens(4));
    }
}