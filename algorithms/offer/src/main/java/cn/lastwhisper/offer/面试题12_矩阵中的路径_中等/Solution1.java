package cn.lastwhisper.offer.面试题12_矩阵中的路径_中等;

import cn.lastwhisper.leetcode.common.matrix.ArrayUtils;

class Solution1 {

    /**
     * 题目地址：https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：递归+回溯
     *  递归：在board起始位置上下左右去搜索，每一个位置保证：
     *      1.与该index单词字母相同
     *      2.数组不越界
     *      3.未被访问过
     *      4.index+1也满足这1、2、3、4条件
     *  回溯：将当前位置标记为未访问
     *  递归终止：index等于单词长度-1，最后一个字母相同
     * -------------------------------------------------------------------
     * 时间复杂度：O(m*n*m*n)
     * 空间复杂度：O(m*n)
     */
    private static final int[][] directory = new int[][]{
            {-1, 0},// up
            {0, 1},// right
            {1, 0},// down
            {0, -1},// left
    };

    private int m, n;

    private boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < m && y < n;
    }

    public boolean exist(char[][] board, String word) {
        m = board.length;
        if (m == 0) {
            return false;
        }
        n = board[0].length;

        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 从word[0]开始搜索board
                if (board[i][j] == word.charAt(0) && findWord(board, i, j, word, 0, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * @param board 矩阵
     * @param startX x
     * @param startY y
     * @param word 寻找的单词
     * @param index 当前对比的位置
     * @param visited 已经访问过得坐标 false表示未访问，true表示已访问
     */
    public boolean findWord(char[][] board, int startX, int startY, String word, int index, boolean[][] visited) {
        if (word.length() - 1 == index) {
            return board[startX][startY] == word.charAt(index);
        }

        if (board[startX][startY] == word.charAt(index)) {
            // 标记来过
            visited[startX][startY] = true;

            for (int i = 0; i < directory.length; i++) {
                int newX = startX + directory[i][0];
                int newY = startY + directory[i][1];
                if (isValid(newX, newY) && !visited[newX][newY] &&
                        findWord(board, newX, newY, word, index + 1, visited)) {
                    return true;
                }
            }
            visited[startX][startY] = false;
        }
        return false;
    }

    public static void main(String[] args) {
        //String[][] board = new String[][]{{"A", "B", "C", "E"}, {"S", "F", "C", "S"}, {"A", "D", "E", "E"}};
        //String word = "ABCCED";
        String[][] board = new String[][]{{"a", "b"}, {"c", "d"}};
        String word = "abcd";
        System.err.println(new Solution1().exist(ArrayUtils.createCharArrays(board), word));
    }
}