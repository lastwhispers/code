package cn.cunchang.N皇后_51_困难;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author cunchang
 * @date 2022/7/1 5:33 PM
 */
class Solution {
    List<List<String>> result = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        char[][] chessboard = new char[n][n];

        for (char[] chess : chessboard) {
            Arrays.fill(chess,'.');
        }

        solveNQueens0(chessboard, 0);
        return result;
    }

    private void solveNQueens0(char[][] chessboard, int row) {
        if (row == chessboard.length) {
            List<String> subResult = getSubResult(chessboard);
            result.add(subResult);
            return;
        }
        int col = chessboard[row].length;
        for (int i = 0; i < col; i++) {
            // 排除不合规
            if (!valid(chessboard, row, i)) {
                continue;
            }
            // 放棋子
            chessboard[row][i] = 'Q';
            // 尝试下一列
            solveNQueens0(chessboard, row + 1);
            // 撤销操作
            chessboard[row][i] = '.';
        }
    }

    private List<String> getSubResult(char[][] chessboard) {
        List<String> subResult = new ArrayList<>(chessboard.length);
        for (int i = 0; i < chessboard.length; i++) {
            StringBuilder rowStr = new StringBuilder();
            for (int j = 0; j < chessboard.length; j++) {
                rowStr.append(chessboard[i][j]);
            }
            subResult.add(rowStr.toString());
        }
        return subResult;
    }

    private boolean valid(char[][] chessboard, int row, int col) {

        // row之前是否同列
        for (int i = 0; i <= row; i++) {
            if (chessboard[i][col] == 'Q') {
                return false;
            }
        }

        // 左对角线；行列坐标减一
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }

        // 右对角线；行坐标减一，列坐标加一
        for (int i = row, j = col; i >= 0 && j < chessboard[0].length; i--, j++) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solveNQueens(10));
    }
}